package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testdata.TestData.borderColorRed;

public class RegistrationPage {
    CalendarComponent calendar = new CalendarComponent();
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateSelect = $("#state");
    private final SelenideElement citySelect = $("#city");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement submitButton = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    @Step("Ввести имя \"{value}\"")
    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Ввести фамилию \"{value}\"")
    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Ввести эл.почту \"{value}\"")
    public RegistrationPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Выбрать пол \"{value}\"")
    public RegistrationPage setGender(String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    @Step("Выбрать дату рождения \"{day}\", \"{month}\", \"{year}\"")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);

        return this;
    }

    @Step("Выбрать предмет \"{value}\"")
    public RegistrationPage typeSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Ввести номер телефона \"{value}\"")
    public RegistrationPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Выбрать хобби \"{value}\"")
    public RegistrationPage setHobbies(String value) {
        hobbiesContainer.$(byText(value)).click();

        return this;
    }

    @Step("Загрузить файл \"{value}\"")
    public RegistrationPage uploadPicture(String value){
        uploadPicture.uploadFromClasspath(value);

        return this;
    }

    @Step("Ввести адрес \"{value}\"")
    public RegistrationPage typeCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    @Step("Выбрать штат \"{value}\"")
    public RegistrationPage setState(String value) {
        stateSelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    @Step("Выбрать город \"{value}\"")
    public RegistrationPage setCity(String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    @Step("Нажатие на кнопку отправки формы Submit")
    public RegistrationPage submitForm () {
        submitButton.click();

        return this;
    }

    public RegistrationPage checkBorderColorUserEmail() {
        userEmailInput.shouldHave(cssValue("border-color", borderColorRed));
        return this;
    }
}
