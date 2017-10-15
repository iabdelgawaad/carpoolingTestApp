package com.example.ibrahimabdelgawad.carpoolingtestapp;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String FAKE_STRING = "loading";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        when(mMockContext.getString(R.string.loading_text))
                .thenReturn(FAKE_STRING);

        // ...then the result should be the expected one.
        assertThat(mMockContext.getString(R.string.loading_text), is(FAKE_STRING));
    }

}