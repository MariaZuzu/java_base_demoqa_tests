package tests.testdata;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String userNumber = faker.phoneNumber().subscriberNumber(10);
    public String genderWrapper = faker.options().option("Male", "Female", "Other");
//    public String day = String.valueOf(faker.number().numberBetween(1, 28));
    public String day = String .format("%02d", faker.number().numberBetween(1, 28));
    public String month = faker.options().option("May", "June", "July", "August");
    public String year = String.valueOf(faker.number().numberBetween(1990, 2005));
    public String subjectsInput = faker.options().option("Chemistry", "Math", "History", "English");
    public String hobbiesWrapper = faker.options().option("Music", "Sports", "Reading");
    public String uploadPicture = faker.options().option("test1.jpeg");
    public String currentAddress = faker.address().fullAddress();
//    public String permanentAddress = faker.address().fullAddress()+"ok";
    public String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = selectCity(state);
    public String selectCity(String state) {

        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
//    public String stateAndCity = "Haryana Karnal";
    public String messageAfterSubmitting = "Thanks for submitting the form";
}
