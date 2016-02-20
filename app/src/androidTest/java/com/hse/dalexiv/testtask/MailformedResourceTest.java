package com.hse.dalexiv.testtask;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MailformedResourceTest extends BaseUrlTest {
    String url = "http://google.ru/invalid_resourse_for_sure";

    @Test
    public void test() {
        super.enterUrl(url);
    }
}
