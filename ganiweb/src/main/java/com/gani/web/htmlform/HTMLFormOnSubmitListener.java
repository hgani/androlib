package com.gani.web.htmlform;

import android.content.Context;

import java.io.Serializable;

public interface HTMLFormOnSubmitListener extends Serializable {
    void onSubmit(HTMLForm form);
}
