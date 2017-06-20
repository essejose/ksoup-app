package com.essejose.ksoupapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {


    String METHOD_NAME = "Soma";
    String SOAP_ACTION = "";

    String NAMESPACE = "http://essejose.com.br/";
    String SOAP_URL = "http://10.3.1.22:8080/Calculadora/Calculadora?WSDL";


    SoapObject request;
    SoapPrimitive calcular;

    ProgressDialog pdialog;

    private EditText value1;

    private EditText value2;

    private TextView res;

    private  int result;
    private  int v1;
    private  int v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1 = (EditText)  findViewById(R.id.valor1);
        value2 = (EditText)  findViewById(R.id.valor2);
        res = (TextView) findViewById(R.id.result);

    }

    public void soma(View view) {

        v1 = Integer.parseInt(value1.getText().toString());
        v2 = Integer.parseInt(value2.getText().toString());

        CalcularAsync calcularAsync = new CalcularAsync();
        calcularAsync.execute(v1,v2);
    }

    private class CalcularAsync extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {

            request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("numero1", params[0]);
            request.addProperty("numero2", params[1]);
            request.addProperty("op", "+");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_URL);
            try {
                httpTransport.call(SOAP_ACTION, envelope);
                calcular = (SoapPrimitive) envelope.getResponse();
            } catch (Exception e) {
                e.getMessage();
            }
            return calcular.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            pdialog.dismiss();

            //Toast.makeText(getApplicationContext(), "Resultado: " + calcular.toString(), Toast.LENGTH_SHORT).show();
            res.setText( calcular.toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(MainActivity.this);
            pdialog.setMessage("Converting...");
            pdialog.show();
        }
    }
}
