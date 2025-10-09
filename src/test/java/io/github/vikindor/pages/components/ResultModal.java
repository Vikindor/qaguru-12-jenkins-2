package io.github.vikindor.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class ResultModal {

    public static final String
            tableTitle = "Thanks for submitting the form",
            tableRowName = "Student Name",
            tableRowEmail = "Student Email",
            tableRowGender = "Gender",
            tableRowMobile = "Mobile",
            tableRowDateOfBirth = "Date of Birth",
            tableRowSubjects = "Subjects",
            tableRowHobby = "Hobbies",
            tableRowPicture = "Picture",
            tableRowAddress = "Address",
            tableRowStateAndCity = "State and City";

    private final SelenideElement modal = $(".modal-dialog");
    private final SelenideElement title = $("#example-modal-sizes-title-lg");
    private final SelenideElement formTable = $(".table-responsive");

    public ResultModal shouldAppear() {
        modal.should(appear);
        return this;
    }

    public ResultModal shouldHaveTitle(String expected) {
        title.shouldHave(exactText(expected));
        return this;
    }

    public ResultModal shouldHaveValue(String key, String value) {
        formTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public ResultModal shouldHaveExact(String key, String expected) {
        SelenideElement row = formTable.$$("tbody tr").findBy(text(key));
        row.$$("td").get(0).shouldHave(exactText(key));
        row.$$("td").get(1).shouldHave(exactText(expected));
        return this;
    }
}
