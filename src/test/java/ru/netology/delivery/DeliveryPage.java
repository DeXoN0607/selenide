package ru.netology.delivery;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryPage {

    private SelenideElement cityInput = $("[data-test-id=city] input");
    private SelenideElement dateInput = $("[data-test-id=date] input");
    private SelenideElement nameInput = $("[data-test-id=name] input");
    private SelenideElement phoneInput = $("[data-test-id=phone] input");
    private SelenideElement agreementCheckbox = $("[data-test-id=agreement]");
    private SelenideElement notification = $("[data-test-id=notification]");

    public DeliveryPage setCity(String city) {
        cityInput.setValue(city);
        return this;
    }

    public DeliveryPage selectCityFromAutocomplete(String city) {
        cityInput.setValue(city.substring(0, 2));
        $$(".menu-item").findBy(text(city)).click();
        return this;
    }

    public void verifyAgreementError() {
        $("[data-test-id=agreement]")
                .shouldHave(cssClass("input_invalid"));
    }

    public DeliveryPage setDate(String date) {
        dateInput.doubleClick().sendKeys(date);
        return this;
    }

    public DeliveryPage setName(String name) {
        nameInput.setValue(name);
        return this;
    }

    public DeliveryPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public DeliveryPage acceptAgreement() {
        agreementCheckbox.click();
        return this;
    }

    public DeliveryPage submit() {
        $$("button").findBy(text("Забронировать")).click();
        return this;
    }

    public void verifySuccess(String expectedDate) {
        notification
                .shouldBe(visible)
                .shouldHave(text("Успешно"))
                .shouldHave(text(expectedDate));
    }
}