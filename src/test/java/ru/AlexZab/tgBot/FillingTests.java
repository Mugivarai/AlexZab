package ru.AlexZab.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.AlexZab.tgBot.entity.Category;
import ru.AlexZab.tgBot.entity.Product;
import ru.AlexZab.tgBot.repository.CategoryRepository;
import ru.AlexZab.tgBot.repository.ProductRepository;

import java.math.BigDecimal;

@SpringBootTest
public class FillingTests
{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Test
    void createTwoClients(){

        categoryRepository.deleteAll();

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
        Product mazzarella = new Product("Маццарелла","Вкусная пицца маццарелла", BigDecimal.valueOf(400.35),pizza);
        productRepository.save(mazzarella);

        Product margarita = new Product("Маргарита","Классика всех пицц", BigDecimal.valueOf(400.00),pizza);
        productRepository.save(margarita);

        Product peperoni = new Product("Пеперони","Пицца с колбасками пеперони", BigDecimal.valueOf(400.00),pizza);
        productRepository.save(peperoni);

        //Классические роллы
        Product filodelfia = new Product("Филодельфия","Классические роллы из Америки", BigDecimal.valueOf(500.00),classicRolls);
        productRepository.save(filodelfia);

        Product colifornia = new Product("Калифорния", "Классические роллы из Америки", BigDecimal.valueOf(600.00), classicRolls);
        productRepository.save(colifornia);

        Product ikitory = new Product("Якитори","Япоснкая классика", BigDecimal.valueOf(700.00),classicRolls);
        productRepository.save(ikitory);

        // Запеченные роллы
        Product bakedRolls1 = new Product("Умами", "Японская классика", BigDecimal.valueOf(700.00), bakedRolls);
        productRepository.save(bakedRolls1);

        Product bakedRolls2 = new Product("Калифорния угорь", "Запеченный угорь, авокадо, огурец, тобико", BigDecimal.valueOf(750.00), bakedRolls);
        productRepository.save(bakedRolls2);

        Product bakedRolls3 = new Product("Филадельфия чиз", "Крем-сыр, лосось, авокадо, огурец", BigDecimal.valueOf(780.00), bakedRolls);
        productRepository.save(bakedRolls3);

        // Сладкие роллы
        Product sweetRolls1 = new Product("Фруктовый рай", "Фрукты, клубника, киви, манго", BigDecimal.valueOf(680.00), sweetRolls);
        productRepository.save(sweetRolls1);

        Product sweetRolls2 = new Product("Ягодное наслаждение", "Клубника, малина, черника", BigDecimal.valueOf(720.00), sweetRolls);
        productRepository.save(sweetRolls2);

        Product sweetRolls3 = new Product("Манго тайфун", "Манго, кокосовый крем, клубника", BigDecimal.valueOf(740.00), sweetRolls);
        productRepository.save(sweetRolls3);

        // Наборы
        Product sets1 = new Product("VIP сет", "Разнообразие роллов и суши", BigDecimal.valueOf(1200.00), sets);
        productRepository.save(sets1);

        Product sets2 = new Product("Фестиваль вкуса", "Набор роллов и суши на большую компанию", BigDecimal.valueOf(1500.00), sets);
        productRepository.save(sets2);

        Product sets3 = new Product("Семейный ужин", "Набор для всей семьи", BigDecimal.valueOf(1100.00), sets);
        productRepository.save(sets3);

        // Классические бургеры
        Product classicBurgers1 = new Product("Чизбургер", "Говяжий стейк, сыр, овощи", BigDecimal.valueOf(500.00), classicBurgers);
        productRepository.save(classicBurgers1);

        Product classicBurgers2 = new Product("Бургер с беконом", "Говяжий бифштекс, сыр, бекон", BigDecimal.valueOf(550.00), classicBurgers);
        productRepository.save(classicBurgers2);

        Product classicBurgers3 = new Product("Двойной чизбургер", "Два говяжьих стейка, двойной сыр", BigDecimal.valueOf(600.00), classicBurgers);
        productRepository.save(classicBurgers3);

        // Острые бургеры
        Product spicyBurgers1 = new Product("Чили-чиз бургер", "Острая котлета, сыр, чили соус", BigDecimal.valueOf(550.00), spicyBurgers);
        productRepository.save(spicyBurgers1);

        Product spicyBurgers2 = new Product("Спайси кари бургер", "Острая куриная котлета, карри соус, овощи", BigDecimal.valueOf(580.00), spicyBurgers);
        productRepository.save(spicyBurgers2);

        Product spicyBurgers3 = new Product("Острые крылышки бургер", "Острые куриные крылышки, маринованный лук, соус", BigDecimal.valueOf(570.00), spicyBurgers);
        productRepository.save(spicyBurgers3);

        // Газированные напитки
        Product carbonatedDrinks1 = new Product("Кока-кола", "Классическая газировка", BigDecimal.valueOf(150.00),sodas);
        productRepository.save(carbonatedDrinks1);

        Product carbonatedDrinks2 = new Product("Пепси", "Классическая газировка", BigDecimal.valueOf(140.00), sodas);
        productRepository.save(carbonatedDrinks2);

        Product carbonatedDrinks3 = new Product("Спрайт", "Лимонная газировка", BigDecimal.valueOf(160.00), sodas);
        productRepository.save(carbonatedDrinks3);

        // Энергетические напитки
        Product energyDrinks1 = new Product("Red Bull", "Классический энергетик", BigDecimal.valueOf(180.00), energyDrinks);
        productRepository.save(energyDrinks1);

        Product energyDrinks2 = new Product("Monster", "Сильный энергетик", BigDecimal.valueOf(170.00), energyDrinks);
        productRepository.save(energyDrinks2);

        Product energyDrinks3 = new Product("Burn", "Фруктовый энергетик", BigDecimal.valueOf(190.00), energyDrinks);
        productRepository.save(energyDrinks3);

        // Соки
        Product juices1 = new Product("Яблочный сок", "Свежевыжатый яблочный сок", BigDecimal.valueOf(120.00), juices);
        productRepository.save(juices1);

        Product juices2 = new Product("Апельсиновый сок", "Свежевыжатый апельсиновый сок", BigDecimal.valueOf(130.00), juices);
        productRepository.save(juices2);

        Product juices3 = new Product("Мультифруктовый сок", "Сок из нескольких видов фруктов", BigDecimal.valueOf(140.00), juices);
        productRepository.save(juices3);

        // Другие напитки
        Product otherDrinks1 = new Product("Минеральная вода", "Без газа", BigDecimal.valueOf(80.00), otherDrinks);
        productRepository.save(otherDrinks1);

        Product otherDrinks2 = new Product("Кофе", "Ароматный кофе", BigDecimal.valueOf(100.00), otherDrinks);
        productRepository.save(otherDrinks2);

        Product otherDrinks3 = new Product("Чай", "Черный чай", BigDecimal.valueOf(90.00), otherDrinks);
        productRepository.save(otherDrinks3);

    }

}
