package ru.AlexZab.tgBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.AlexZab.tgBot.entity.ClientOrder;
import ru.AlexZab.tgBot.entity.OrderProduct;
import ru.AlexZab.tgBot.entity.Product;
import ru.AlexZab.tgBot.entity.Client;
import ru.AlexZab.tgBot.service.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TgBotService {

    private final TelegramBot bot = new TelegramBot("7232727694:AAG_r-eP7EruBz-twFWSuBSlmDSMiFFpCGg");

    private final ClientService clientService;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final ClientOrderService clientOrderService;

    private final OrderProductService orderProductService;

    private final List<String> allCategories;

    //Временные переменные для регистрации пользователя
    private final Map<Long,Registration> userRegistrationMap = new HashMap<>();

    //Постоянные переменные для проверки состояния пользователя
    private final Map<Long,UserState> userStateMap = new HashMap<>();

    public TgBotService(ClientService clientService,
                        ProductService productService,
                        CategoryService categoryService,
                        ClientOrderService clientOrderService,
                        OrderProductService orderProductService
    ) {
        this.clientService = clientService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.clientOrderService = clientOrderService;
        this.allCategories = categoryService.getNameAllCategories()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        this.orderProductService = orderProductService;
    }

    @PostConstruct
    private void start(){

        bot.setUpdatesListener(updates ->{

            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private void processUpdate(Update update){

        if(update.callbackQuery()!=null){
            callBackMethod(update.callbackQuery());
            return;
        }

        if(update.message()==null)return;

        Long id = update.message().chat().id();

        UserState userState = userStateMap.getOrDefault(id, new UserState());

        //Проверка на событие регистрации
        if(!userState.registrationState){
            Registration registration = userRegistrationMap.getOrDefault(id,new Registration());
            registration(registration,update,userState);
            userRegistrationMap.put(id, registration);
            userStateMap.put(id, userState);
            return;
        }

        //Проверка. Является ли категория листом или узлом
        if(allCategories.contains(update.message().text().toLowerCase())){
            if(categoryService.hasChildCategories(
                    categoryService.getIdCategoryByName(
                            update.message().text().toLowerCase()
                    )
            )){
                //Если узел, выыводим дочерни листы или узлы
                catalogEatLvl(id,update.message().text());
                return;
            }else{
                //Если лист, выводим подукты с категорией листа
                catalogProduct(id,update.message().text());
                return;
            }
        }

        switch (update.message().text().toLowerCase()){

            case "/start" -> {
                Client client = clientService.getClientByExternalId(id);

                if(client!=null){

                    String temp = client.getFullName().split(" ")[0];
                    temp += ", давайте начнем есть!";
                    SendMessage message = new SendMessage(id,temp);
                    bot.execute(message);

                }else{

                    String temp = "Добро пожаловать, любитель вкусной еды! Давай заполним о тебе" +
                            " некоторую информацию, чтобы наш куръер смог до тебя добратья!\n" +
                            "Введите ваше ФИО:";
                    SendMessage message = new SendMessage(id,temp);

                    userState.registrationState = false;
                    userRegistrationMap.put(id, new Registration());
                    userStateMap.put(id, userState);
                    bot.execute(message);
                }
                break;
            }

            case "привет" -> {
                String temp = "Привет, я готов стать твоим помощиком!";
                SendMessage message = new SendMessage(id,temp);
                bot.execute(message);
                break;
            }

            case "пока" -> {
                String temp = "Приятного аппетита!";
                SendMessage message = new SendMessage(id,temp);
                bot.execute(message);
                break;
            }

            case "в основное меню" , "меню" -> {
                mainMenu(id);
                break;
            }

            case "каталог еды" -> {
                catalogEat(id);
                break;
            }

            case "оформить заказ" ->{
                createOrder(id);
                break;
            }

            default -> {
                ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup("Меню");
                keyboard.resizeKeyboard(true);
                SendMessage message = new SendMessage(id,
                        "Я не знаю что делать").replyMarkup(keyboard);
                bot.execute(message);
                break;
            }
        }

    }

    private void registration(Registration temp,Update update,UserState userState){
        Long id = update.message().chat().id();

        if(temp.fullName==null){

            temp.fullName = update.message().text();

            SendMessage message = new SendMessage(id,"Введите ваш номер телефона");
            bot.execute(message);

        } else if (temp.phone==null) {

            temp.phone = update.message().text();

            SendMessage message = new SendMessage(id,"Введите адресс доставки");
            bot.execute(message);

        } else if (temp.address == null) {

            temp.address = update.message().text();

            SendMessage message = new SendMessage(id,"Регистрация прошла успешно\n" +
                    "Давайте заказывать вкусную еду");
            bot.execute(message);
            userState.registrationState = true;

            Client client = new Client(id,temp.fullName, temp.phone, temp.address);
            clientService.save(client);

            ClientOrder clientOrder = new ClientOrder(1,BigDecimal.valueOf(0),client);
            clientOrderService.save(clientOrder);

            userRegistrationMap.remove(id);
        }
    }

    private void mainMenu(Long id){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new String[]{"Каталог еды", "Сменить адрес"},
                new String[]{"Сменить номер телефона", "Сменить имя"}
        ).resizeKeyboard(true);
        SendMessage message = new SendMessage(id, "Выберите действие:").
                replyMarkup(keyboard);
        bot.execute(message);
    }

    private void catalogEat(Long id){

        List<String> names = categoryService.getNameCategoryByParentId(null);
        String[] keyboardArray = new String[names.size()];

        for (int i = 0; i < names.size(); i++) {
            keyboardArray[i] = names.get(i);
        }
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(keyboardArray);

        keyboard.addRow("В основное меню");
        keyboard.addRow("Оформить заказ");
        keyboard.resizeKeyboard(true);
        SendMessage message = new SendMessage(id,"Категории еды:").
                replyMarkup(keyboard);
        bot.execute(message);
    }

    private void catalogEatLvl(Long id,String str){
        List<String> names = categoryService.getNameCategoryByParentId(
                categoryService.getIdCategoryByName(str.toLowerCase())
        );
        String[] keyboardArray = new String[names.size()];

        for (int i = 0; i < names.size(); i++) {
            keyboardArray[i] = names.get(i);
        }
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(keyboardArray);

        keyboard.addRow("Оформить заказ");
        keyboard.addRow("В основное меню");
        keyboard.resizeKeyboard(true);
        SendMessage message = new SendMessage(id,"Категория "+str.toLowerCase()+":").
                replyMarkup(keyboard);
        bot.execute(message);
    }

    private void catalogProduct(long id,String str){
        str = str.toLowerCase();
        List<Product> products = productService.getProductsByCategoryId(
                categoryService.getIdCategoryByName(str)
        );
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        for(Product product: products){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(String.format("Товар %s. Цена %.2f руб.",
                      product.getName(), product.getPrice()));
            button.setCallbackData(String.format("product:%d", product.getId()));
            markup.addRow(button);
        }
        bot.execute(new SendMessage(id, "Товары").replyMarkup(markup));
    }

    private void callBackMethod(CallbackQuery callbackQuery){
        Long chatId = callbackQuery.from().id();
        Client client = clientService.getClientByExternalId(chatId);
        Long clientId = client.getId();
        Long clientOrderId = clientOrderService.getClientOrderByClientIdAndStatusOne(clientId).getId();
        Long productId = Long.parseLong(callbackQuery.data().split(":")[1]);

        if(orderProductService.hasProduct(clientOrderId,productId)){
            orderProductService.updateCountProduct(clientOrderId,productId);
        }else{
            OrderProduct orderProduct = new OrderProduct(1,
                    productService.getProductById(productId),
                    clientOrderService.getClientOrderByClientIdAndStatusOne(clientId)
            );
            orderProductService.save(orderProduct);
        }
        String name = productService.getNameProductById(productId);
        bot.execute(new SendMessage(callbackQuery.from().id(),name+": добавлен в заказ"));

    }

    private void createOrder(Long id){

        Long clientId = clientService.getClientByExternalId(id).getId();
        Long clientOrderId = clientOrderService.getClientOrderByClientIdAndStatusOne(clientId).getId();
        BigDecimal total = orderProductService.getTotalOrderProduct(clientOrderId); // Используем BigDecimal вместо Double
        List<Object[]> results = orderProductService.findOrderProductDetails(clientOrderId);

        String temp = "";
        for (Object[] result : results) {
            String productName = (String) result[0];
            int countProduct = (Integer) result[1];
            BigDecimal price = (BigDecimal) result[2]; // Используем BigDecimal

            BigDecimal countBigDecimal = BigDecimal.valueOf(countProduct);
            BigDecimal totalProductPrice = price.multiply(countBigDecimal); // Умножаем BigDecimal на BigDecimal

            temp += String.format("%s: %d * %.2f = %.2f руб.\n", productName, countProduct,
                    price.doubleValue(), totalProductPrice.doubleValue()); // Преобразуем BigDecimal в double для форматирования вывода
        }
        temp += String.format("Итого: %.2f", total.doubleValue()); // Преобразуем BigDecimal в double для форматирования вывода
        bot.execute(new SendMessage(id, temp));
        clientOrderService.updateStatus(clientId,total);

        ClientOrder clientOrder = new ClientOrder(1,BigDecimal.valueOf(0),
                clientService.getClientByExternalId(id));
        clientOrderService.save(clientOrder);

    }

    private class UserState {

        public Boolean registrationState;

        public UserState() {
            this.registrationState = true;
        }
    }

    private class Registration {

        public String fullName;
        public String phone;
        public String address;

    }



}
