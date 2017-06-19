package com.essejose.ksoupapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class MainActivity extends AppCompatActivity {


    String METHOD_NAME = "calcular";
    String SOAP_ACTION = "";

    String NAMESPACE = "http://essejose.com.br/";
    String SOAP_URL = "http://10.3.1.22:8080/Calculadora/Calculadora?WSDL";


    SoapObject request;
    SoapPrimitive calcular;

    ProgressDialog pdialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
