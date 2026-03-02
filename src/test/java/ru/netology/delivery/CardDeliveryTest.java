package ru.netology.delivery;

import org.junit.jupiter.api.Test;
import ru.netology.delivery.DeliveryPage;

public class CardDeliveryTest extends BaseTest {

    DeliveryPage page = new DeliveryPage();

    @Test
    void shouldSuccessfullyBookCard() {
        String meetingDate = DataGenerator.generateDate(4);

        page.setCity("Москва")
                .setDate(meetingDate)
                .setName("Иванов Иван")
                .setPhone("+79991234567")
                .acceptAgreement()
                .submit()
                .verifySuccess(meetingDate);
    }

    @Test
    void shouldBookCardUsingAutocomplete() {
        String meetingDate = DataGenerator.generateDate(5);

        page.selectCityFromAutocomplete("Москва")
                .setDate(meetingDate)
                .setName("Иванов Иван")
                .setPhone("+79991234567")
                .acceptAgreement()
                .submit()
                .verifySuccess(meetingDate);
    }

    @Test
    void shouldFailIfAgreementNotAccepted() {
        String meetingDate = DataGenerator.generateDate(4);

        page.setCity("Москва")
                .setDate(meetingDate)
                .setName("Иванов Иван")
                .setPhone("+79991234567")
                .submit();

        page.verifyAgreementError();
    }
}