package ru.netology.banklogin.test;

import org.junit.jupiter.api.*;
import ru.netology.banklogin.data.DataHelper;
import ru.netology.banklogin.data.SQLHelper;
import ru.netology.banklogin.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.banklogin.data.SQLHelper.cleanAuthCodes;
import static ru.netology.banklogin.data.SQLHelper.cleanDataBase;

public class BankLoginTest {
    LoginPage loginPage;
    DataHelper.AuthInfo authInfo = DataHelper.getAuthInfoWithTestData();

    @AfterAll
    static void tearDownAll() {
         cleanDataBase();
    }

    @AfterEach
    void tearDown() {
        cleanAuthCodes();
    }



    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999" , LoginPage.class);
    }

    @Test
    void shouldSuccessfullLogin() {
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}
