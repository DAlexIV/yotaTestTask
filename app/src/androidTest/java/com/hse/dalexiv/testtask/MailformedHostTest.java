package com.hse.dalexiv.testtask;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MailformedHostTest extends BaseUrlTest {
    String url = "http://longsuperlongurlthatdoesntexist.ru";

    @Test
    public void test() {
        super.enterUrl(url);
    }
}
