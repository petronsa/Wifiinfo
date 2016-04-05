package company.petron.wifiinfo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity_wifi_info extends AppCompatActivity {

    TextView textEstado, textIP, textSSID, textSIDOculto;
    TextView textBSSID, textMAC, textVelocidad, textRSSI;
    String infoIP, infoSSID, infoBSSID,
            infoSSIDOculto, infoEstado, infoRSSID,
            infoMAC, infoVelocidad;
    WifiManager wifiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_wifi_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        textEstado = (TextView)findViewById(R.id.txtEstado);
        textIP = (TextView)findViewById(R.id.txtIP);

        textSSID = (TextView)findViewById(R.id.txtSSID);
        textSIDOculto = (TextView)findViewById(R.id.txtSSIDOculto);
        textBSSID = (TextView)findViewById(R.id.txtBSSID);
        textMAC = (TextView)findViewById(R.id.txtMAC);
        textVelocidad = (TextView)findViewById(R.id.txtVelocidad);
        textRSSI = (TextView)findViewById(R.id.txtRSSI);


        ConnectivityManager mAdministradorConexion =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mInformacionRed =
                mAdministradorConexion.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager mAdministradorWifi =
                (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo mInformacionWifi = mAdministradorWifi.getConnectionInfo();

        if (mInformacionRed.isConnected())
        {
            int myIp = mInformacionWifi.getIpAddress();
            int intMyIp3 = myIp/0x1000000;
            int intMyIp3mod = myIp%0x1000000;
            int intMyIp2 = intMyIp3mod/0x10000;
            int intMyIp2mod = intMyIp3mod%0x10000;
            int intMyIp1 = intMyIp2mod/0x100;
            int intMyIp0 = intMyIp2mod%0x100;

            infoIP = "IP: " + String.valueOf(intMyIp0)
                    + "." + String.valueOf(intMyIp1)
                    + "." + String.valueOf(intMyIp2)
                    + "." + String.valueOf(intMyIp3);
            infoEstado = "Estado: Conectado a red Wifi";
            infoVelocidad = String.valueOf("Velocidad: " +
                    mInformacionWifi.getLinkSpeed()) + " " +
                    WifiInfo.LINK_SPEED_UNITS;
            infoSSID = "SSID: " + mInformacionWifi.getSSID();
            infoBSSID = "BSSID: " + mInformacionWifi.getBSSID();
            infoRSSID = "RSSI: " + mInformacionWifi.getRssi();
            infoMAC = "MAC: " + mInformacionWifi.getMacAddress();
            if (!mInformacionWifi.getHiddenSSID())
                infoSSIDOculto = "SSID oculto: No";
            else
                infoSSIDOculto = "SSID oculto: Sí";

            textIP.setText(infoIP);
            textEstado.setText(infoEstado);
            textVelocidad.setText(infoVelocidad);
            textSSID.setText(infoSSID);
            textSIDOculto.setText(infoSSIDOculto);
            textBSSID.setText(infoBSSID);
            textRSSI.setText(infoRSSID);
            textMAC.setText(infoMAC);
        }
        else
        {
            infoIP = "IP: --";
            infoEstado = "Estado: No conectado a Red Wifi";
            infoVelocidad = "Velocidad: --";
            infoSSID = "SSID: --";
            infoSSIDOculto = "SSID oculto: --";
            infoBSSID = "BSSID: --";
            infoRSSID = "RSSI: --";
            infoMAC = "MAC: --";

            textEstado.setText(infoEstado);
            textIP.setText(infoIP);
            textVelocidad.setText(infoVelocidad);
            textSSID.setText(infoSSID);
            textSIDOculto.setText(infoSSIDOculto);
            textBSSID.setText(infoBSSID);
            textRSSI.setText(infoRSSID);
            textMAC.setText(infoMAC);
        }

    }

        public void wifi (View view)
        {
            EstadoWifi();
        }

        public void estadowifi(boolean valor)
        {


        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu_main_activity_wifi_info, menu);
         return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu){
        MenuItem menuItemWifiOff = menu.findItem(R.id.estado_wifi_off);
        MenuItem menuItemWifiOn= menu.findItem(R.id.estado_wifi_on);

        if (wifiManager.isWifiEnabled()){
            menuItemWifiOn.setEnabled(true).setVisible(true);
            menuItemWifiOff.setEnabled(false).setVisible(false);
        }
        else {
            menuItemWifiOn.setEnabled(false).setVisible(false);
            menuItemWifiOff.setEnabled(true).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }
    public void EstadoWifi()
    {
        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.estado_wifi_on:
                wifiManager.setWifiEnabled(true);
                EstadoWifi();
               invalidateOptionsMenu();
            case R.id.estado_wifi_off:
                wifiManager.setWifiEnabled(false);
                EstadoWifi();
                invalidateOptionsMenu();

            return true;

            case R.id.ayuda:
                //boton ayuda

                return true;

            case R.id.votar:
                //boton votar
                Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=company.petron.imei");
                Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(intent);
                return true;
            case R.id.reiniciar:
                //reinicia la aplicación
                Intent reinicio = getIntent();
                finish();
                startActivity(reinicio);

                return true;
            case R.id.salir:
                //botón salir
               // MainActivity_imei.this.startAppAd.showAd();
               // MainActivity_imei.this.startAppAd.loadAd();
                finish();
                return true;
            case R.id.action_settings:
                //botón salir
                //MainActivity_imei.this.startAppAd.showAd();
                //MainActivity_imei.this.startAppAd.loadAd();
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
    }
    }

}
