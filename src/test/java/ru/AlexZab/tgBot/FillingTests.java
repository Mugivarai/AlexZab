package ru.AlexZab.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.AlexZab.tgBot.entity.*;
import ru.AlexZab.tgBot.repository.*;

import java.math.BigDecimal;

@SpringBootTest
public class FillingTests
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Test
    void createTwoClients(){

        Category pizza = new Category("Пицца",null);
        categoryRepository.save(pizza);

        Category rolls = new Category("Роллы",null);
        categoryRepository.save(rolls);

        Category burgers = new Category("Бургеры",null);
        categoryRepository.save(burgers);

        Category drinks = new Category("Напитки",null);
        categoryRepository.save(drinks);

        // Создаем подкатегории для категории Роллы
        Category classicRolls = new Category("Классические роллы",rolls);
        categoryRepository.save(classicRolls);

        Category bakedRolls = new Category("Запеченные роллы",rolls);
        categoryRepository.save(bakedRolls);

        Category sweetRolls = new Category("Сладкие роллы",rolls);
        categoryRepository.save(sweetRolls);

        Category sets = new Category("Наборы",rolls);
        categoryRepository.save(sets);

        // Создаем подкатегории для категории Бургеры
        Category classicBurgers = new Category("Классические бургеры",burgers);
        categoryRepository.save(classicBurgers);

        Category spicyBurgers = new Category("Острые бургеры",burgers);
        categoryRepository.save(spicyBurgers);

        //Создаем подкатегории для категории Напитки
        Category sodas = new Category("Газированные напитки",drinks);
        categoryRepository.save(sodas);

        Category energyDrinks = new Category("Энергетические напитки",drinks);
        categoryRepository.save(energyDrinks);

        Category juices = new Category("Соки",drinks);
        categoryRepository.save(juices);

        Category otherDrinks = new Category("Другие",drinks);
        categoryRepository.save(otherDrinks);

        //Пицца
        Product mazzarella = new Product("Маццарелла",
                "Вкусная пицца маццарелла",
                BigDecimal.valueOf(400.35),pizza);
        productRepository.save(mazzarella);

        Product margarita = new Product("Маргарита",
                "Классика всех пицц",
                BigDecimal.valueOf(400.00),pizza);
        productRepository.save(margarita);

        Product peperoni = new Product("Пеперони",
                "Пицца с колбасками пеперони",
                BigDecimal.valueOf(400.00),pizza);
        productRepository.save(peperoni);

        //Классические роллы
        Product filodelfia = new Product("Филодельфия",
                "Классические роллы из Америки",
                BigDecimal.valueOf(500.00),classicRolls);
        productRepository.save(filodelfia);

        Product colifornia = new Product("Калифорния",
                "Классические роллы из Америки",
                BigDecimal.valueOf(600.00), classicRolls);
        productRepository.save(colifornia);

        Product ikitory = new Product("Якитори",
                "Япоснкая классика",
                BigDecimal.valueOf(700.00),classicRolls);
        productRepository.save(ikitory);

        // Запеченные роллы
        Product bakedRolls1 = new Product("Умами",
                "Японская классика",
                BigDecimal.valueOf(700.00), bakedRolls);
        productRepository.save(bakedRolls1);

        Product bakedRolls2 = new Product("Калифорния угорь",
                "Запеченный угорь, авокадо, огурец, тобико",
                BigDecimal.valueOf(750.00), bakedRolls);
        productRepository.save(bakedRolls2);

        Product bakedRolls3 = new Product("Филадельфия чиз",
                "Крем-сыр, лосось, авокадо, огурец",
                BigDecimal.valueOf(780.00), bakedRolls);
        productRepository.save(bakedRolls3);

        // Сладкие роллы
        Product sweetRolls1 = new Product("Фруктовый рай",
                "Фрукты, клубника, киви, манго",
                BigDecimal.valueOf(680.00), sweetRolls);
        productRepository.save(sweetRolls1);

        Product sweetRolls2 = new Product("Ягодное наслаждение",
                "Клубника, малина, черника",
                BigDecimal.valueOf(720.00), sweetRolls);
        productRepository.save(sweetRolls2);

        Product sweetRolls3 = new Product("Манго тайфун",
                "Манго, кокосовый крем, клубника",
                BigDecimal.valueOf(740.00), sweetRolls);
        productRepository.save(sweetRolls3);

        // Наборы
        Product sets1 = new Product("VIP сет",
                "Разнообразие роллов и суши",
                BigDecimal.valueOf(1200.00), sets);
        productRepository.save(sets1);

        Product sets2 = new Product("Фестиваль вкуса",
                "Набор роллов и суши на большую компанию",
                BigDecimal.valueOf(1500.00), sets);
        productRepository.save(sets2);

        Product sets3 = new Product("Семейный ужин",
                "Набор для всей семьи",
                BigDecimal.valueOf(1100.00), sets);
        productRepository.save(sets3);

        // Классические бургеры
        Product classicBurgers1 = new Product("Чизбургер",
                "Говяжий стейк, сыр, овощи",
                BigDecimal.valueOf(500.00), classicBurgers);
        productRepository.save(classicBurgers1);

        Product classicBurgers2 = new Product("Бургер с беконом",
                "Говяжий бифштекс, сыр, бекон",
                BigDecimal.valueOf(550.00), classicBurgers);
        productRepository.save(classicBurgers2);

        Product classicBurgers3 = new Product("Двойной чизбургер",
                "Два говяжьих стейка, двойной сыр",
                BigDecimal.valueOf(600.00), classicBurgers);
        productRepository.save(classicBurgers3);

        // Острые бургеры
        Product spicyBurgers1 = new Product("Чили-чиз бургер",
                "Острая котлета, сыр, чили соус",
                BigDecimal.valueOf(550.00), spicyBurgers);
        productRepository.save(spicyBurgers1);

        Product spicyBurgers2 = new Product("Спайси кари бургер",
                "Острая куриная котлета, карри соус, овощи",
                BigDecimal.valueOf(580.00), spicyBurgers);
        productRepository.save(spicyBurgers2);

        Product spicyBurgers3 = new Product("Острые крылышки бургер",
                "Острые куриные крылышки, маринованный лук, соус",
                BigDecimal.valueOf(570.00), spicyBurgers);
        productRepository.save(spicyBurgers3);

        // Газированные напитки
        Product carbonatedDrinks1 = new Product("Кока-кола",
                "Классическая газировка",
                BigDecimal.valueOf(150.00),sodas);
        productRepository.save(carbonatedDrinks1);

        Product carbonatedDrinks2 = new Product("Пепси",
                "Классическая газировка",
                BigDecimal.valueOf(140.00), sodas);
        productRepository.save(carbonatedDrinks2);

        Product carbonatedDrinks3 = new Product("Спрайт",
                "Лимонная газировка",
                BigDecimal.valueOf(160.00), sodas);
        productRepository.save(carbonatedDrinks3);

        // Энергетические напитки
        Product energyDrinks1 = new Product("Red Bull",
                "Классический энергетик",
                BigDecimal.valueOf(180.00), energyDrinks);
        productRepository.save(energyDrinks1);

        Product energyDrinks2 = new Product("Monster",
                "Сильный энергетик",
                BigDecimal.valueOf(170.00), energyDrinks);
        productRepository.save(energyDrinks2);

        Product energyDrinks3 = new Product("Burn",
                "Фруктовый энергетик",
                BigDecimal.valueOf(190.00), energyDrinks);
        productRepository.save(energyDrinks3);

        // Соки
        Product juices1 = new Product("Яблочный сок",
                "Свежевыжатый яблочный сок",
                BigDecimal.valueOf(120.00), juices);
        productRepository.save(juices1);

        Product juices2 = new Product("Апельсиновый сок",
                "Свежевыжатый апельсиновый сок",
                BigDecimal.valueOf(130.00), juices);
        productRepository.save(juices2);

        Product juices3 = new Product("Мультифруктовый сок",
                "Сок из нескольких видов фруктов",
                BigDecimal.valueOf(140.00), juices);
        productRepository.save(juices3);

        // Другие напитки
        Product otherDrinks1 = new Product("Минеральная вода",
                "Без газа",
                BigDecimal.valueOf(80.00), otherDrinks);
        productRepository.save(otherDrinks1);

        Product otherDrinks2 = new Product("Кофе",
                "Ароматный кофе",
                BigDecimal.valueOf(100.00), otherDrinks);
        productRepository.save(otherDrinks2);

        Product otherDrinks3 = new Product("Чай",
                "Черный чай",
                BigDecimal.valueOf(90.00), otherDrinks);
        productRepository.save(otherDrinks3);

        //Клиенты
        Client client1 = new Client(1L, "Алексей Мурдрик Валентинович",
                "+79780152341",
                "Улица ПОР дом 31");
        clientRepository.save(client1);

        Client client2 = new Client(2L, "Иванов Иван Иванович",
                "+79123456789",
                "Ленина улица, 10");
        clientRepository.save(client2);

        Client client3 = new Client(3L, "Мария Петровна Сидорова",
                "+79031234567",
                "Пушкинская площадь, 5");
        clientRepository.save(client3);

        Client client4 = new Client(4L, "Петр Васильевич Кузнецов",
                "+79876543210",
                "Московский проспект, 25");
        clientRepository.save(client4);

        Client client5 = new Client(5L, "Елена Николаевна Смирнова",
                "+79001112233",
                "Кирова улица, 3");
        clientRepository.save(client5);

        Client client6 = new Client(6L, "Андрей Дмитриевич Петров",
                "+79998887766",
                "Гагарина проспект, 17");
        clientRepository.save(client6);

        Client client7 = new Client(7L, "Светлана Александровна Иванова",
                "+79777778888",
                "Советская улица, 12");
        clientRepository.save(client7);

        Client client8 = new Client(8L, "Владимир Сергеевич Козлов",
                "+79666665555",
                "Новая улица, 8");
        clientRepository.save(client8);

        Client client9 = new Client(9L, "Ольга Викторовна Зайцева",
                "+79555554444",
                "Красная площадь, 1");
        clientRepository.save(client9);

        Client client10 = new Client(10L, "Дмитрий Владимирович Лебедев",
                "+79444443333",
                "Первомайская улица, 22");
        clientRepository.save(client10);

        Client client11 = new Client(11L, "Наталья Игоревна Морозова",
                "+79333332222",
                "Калинина проспект, 14");
        clientRepository.save(client11);

        Client client12 = new Client(12L, "Александр Павлович Новиков",
                "+79222221111",
                "Октябрьская улица, 7");
        clientRepository.save(client12);

        Client client13 = new Client(13L, "Евгений Николаевич Куликов",
                "+79111110000",
                "Садовая улица, 30");
        clientRepository.save(client13);

        Client client14 = new Client(14L, "Татьяна Васильевна Соловьева",
                "+79000001111",
                "Лермонтова улица, 9");
        clientRepository.save(client14);

        Client client15 = new Client(15L, "Ирина Владимировна Жукова",
                "+79888889999",
                "Советская площадь, 6");
        clientRepository.save(client15);

        //Заказы
        ClientOrder zakaz1 = new ClientOrder(1, BigDecimal.valueOf(800.00), client1);
        clientOrderRepository.save(zakaz1);

        ClientOrder zakaz2 = new ClientOrder(2, BigDecimal.valueOf(400.00), client2);
        clientOrderRepository.save(zakaz2);

        ClientOrder zakaz3 = new ClientOrder(0, BigDecimal.valueOf(500.00), client3);
        clientOrderRepository.save(zakaz3);

        ClientOrder zakaz4 = new ClientOrder(3, BigDecimal.valueOf(600.00), client4);
        clientOrderRepository.save(zakaz4);

        ClientOrder zakaz5 = new ClientOrder(1, BigDecimal.valueOf(700.00), client5);
        clientOrderRepository.save(zakaz5);

        ClientOrder zakaz6 = new ClientOrder(2, BigDecimal.valueOf(700.00), client6);
        clientOrderRepository.save(zakaz6);

        ClientOrder zakaz7 = new ClientOrder(0, BigDecimal.valueOf(750.00), client7);
        clientOrderRepository.save(zakaz7);

        ClientOrder zakaz8 = new ClientOrder(3, BigDecimal.valueOf(780.00), client8);
        clientOrderRepository.save(zakaz8);

        ClientOrder zakaz9 = new ClientOrder(1, BigDecimal.valueOf(680.00), client9);
        clientOrderRepository.save(zakaz9);

        ClientOrder zakaz10 = new ClientOrder(2, BigDecimal.valueOf(720.00), client10);
        clientOrderRepository.save(zakaz10);

        //Заказы продуктов
        OrderProduct orderProduct1 = new OrderProduct(2,margarita,zakaz1);
        orderProductRepository.save(orderProduct1);

        OrderProduct orderProduct2 = new OrderProduct(1,peperoni,zakaz2);
        orderProductRepository.save(orderProduct2);

        OrderProduct orderProduct3 = new OrderProduct(1,juices1,zakaz3);
        orderProductRepository.save(orderProduct3);

        OrderProduct orderProduct4 = new OrderProduct(1,otherDrinks1,zakaz3);
        orderProductRepository.save(orderProduct4);

        OrderProduct orderProduct5 = new OrderProduct(2,carbonatedDrinks1,zakaz3);
        orderProductRepository.save(orderProduct5);

        OrderProduct orderProduct6 = new OrderProduct(1,colifornia,zakaz4);
        orderProductRepository.save(orderProduct6);

        OrderProduct orderProduct7 = new OrderProduct(1,ikitory,zakaz5);
        orderProductRepository.save(orderProduct7);

        OrderProduct orderProduct8 = new OrderProduct(1,ikitory,zakaz6);
        orderProductRepository.save(orderProduct8);

        OrderProduct orderProduct9 = new OrderProduct(1,filodelfia,zakaz7);
        orderProductRepository.save(orderProduct9);

        OrderProduct orderProduct10 = new OrderProduct(1,carbonatedDrinks1,zakaz7);
        orderProductRepository.save(orderProduct10);

        OrderProduct orderProduct11 = new OrderProduct(1,bakedRolls1,zakaz8);
        orderProductRepository.save(orderProduct11);

        OrderProduct orderProduct12 = new OrderProduct(1,otherDrinks1,zakaz8);
        orderProductRepository.save(orderProduct12);

        OrderProduct orderProduct13 = new OrderProduct(1,sweetRolls1,zakaz9);
        orderProductRepository.save(orderProduct13);

        OrderProduct orderProduct14 = new OrderProduct(1,colifornia,zakaz10);
        orderProductRepository.save(orderProduct14);

        OrderProduct orderProduct15 = new OrderProduct(1,carbonatedDrinks1,zakaz10);
        orderProductRepository.save(orderProduct15);

    }

}
