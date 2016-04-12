package company.petron.wifiinfo;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class MainActivity_wifi_info extends AppCompatActivity {

    TextView textEstado, textIP, textSSID, textSIDOculto;
    TextView textBSSID, textMAC, textVelocidad, textRSSI,textSeñal;
    String infoIP, infoSSID, infoBSSID,
            infoSSIDOculto, infoEstado, infoRSSID,
            infoMAC, infoVelocidad,infoNodatos;
    WifiManager wifiManager;
    int numseñal,nunrssi ;
    String letseñal;
    private StartAppAd startAppAd = new StartAppAd(this);




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "101423750", "203503005", true);
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
        textEstado = (TextView) findViewById(R.id.txtEstado);
        textIP = (TextView) findViewById(R.id.txtIP);
        textSSID = (TextView) findViewById(R.id.txtSSID);
        textSIDOculto = (TextView) findViewById(R.id.txtSSIDOculto);
        textBSSID = (TextView) findViewById(R.id.txtBSSID);
        textMAC = (TextView) findViewById(R.id.txtMAC);
        textVelocidad = (TextView) findViewById(R.id.txtVelocidad);
        textRSSI = (TextView) findViewById(R.id.txtRSSI);
        textSeñal = (TextView) findViewById(R.id.txtseñal);



        ConnectivityManager mAdministradorConexion =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mInformacionRed =
                mAdministradorConexion.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager mAdministradorWifi =
                (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo mInformacionWifi = mAdministradorWifi.getConnectionInfo();


        if (mInformacionRed.isConnected()) {
            int myIp = mInformacionWifi.getIpAddress();
            int intMyIp3 = myIp / 0x1000000;
            int intMyIp3mod = myIp % 0x1000000;
            int intMyIp2 = intMyIp3mod / 0x10000;
            int intMyIp2mod = intMyIp3mod % 0x10000;
            int intMyIp1 = intMyIp2mod / 0x100;
            int intMyIp0 = intMyIp2mod % 0x100;

            infoIP = " " + String.valueOf(intMyIp0)
                    + "." + String.valueOf(intMyIp1)
                    + "." + String.valueOf(intMyIp2)
                    + "." + String.valueOf(intMyIp3);
            infoEstado = (getResources().getString(R.string.estado_conectado));
            infoVelocidad = " " + String.valueOf(
                    mInformacionWifi.getLinkSpeed()) + " " +
                    WifiInfo.LINK_SPEED_UNITS;
            infoSSID = " " + mInformacionWifi.getSSID();
            infoBSSID = " " + mInformacionWifi.getBSSID();
            infoRSSID = (getResources().getString(R.string.rssi)) + " " + mInformacionWifi.getRssi();
            infoMAC = " " + mInformacionWifi.getMacAddress();
            if (!mInformacionWifi.getHiddenSSID())
                infoSSIDOculto = (getResources().getString(R.string.ssid_oculto_no));
            else
                infoSSIDOculto = (getResources().getString(R.string.ssid_oculto_si));

            textIP.setText(getResources().getString(R.string.ip) + infoIP);
            textEstado.setText(infoEstado);
            textVelocidad.setText(getResources().getString(R.string.velocidad) + infoVelocidad);
            textSSID.setText(getResources().getString(R.string.ssid) + infoSSID);
            textSIDOculto.setText(infoSSIDOculto);
            textBSSID.setText(getResources().getString(R.string.bssid) + infoBSSID);
            textRSSI.setText(infoRSSID);
            textMAC.setText(getResources().getString(R.string.mac) + infoMAC);
            nunrssi = (mInformacionWifi.getRssi());
            numseñal = 100 + (mInformacionWifi.getRssi());
            letseñal = String.valueOf(numseñal);
            textSeñal.setText(getResources().getString(R.string.señal) +" " + letseñal + "%");


        } else {

            infoEstado = (getResources().getString(R.string.estado_desconectado));
            infoNodatos = " --";

            textEstado.setText(infoEstado);
            textIP.setText(getResources().getString(R.string.ip) + infoNodatos);
            textVelocidad.setText(getResources().getString(R.string.velocidad) + infoNodatos);
            textSSID.setText(getResources().getString(R.string.ssid) + infoNodatos);
            textSIDOculto.setText(getResources().getString(R.string.ssid_oculto) + infoNodatos);
            textBSSID.setText(getResources().getString(R.string.bssid) + infoNodatos);
            textRSSI.setText(getResources().getString(R.string.rssi) + infoNodatos);
            textMAC.setText(getResources().getString(R.string.mac) + infoNodatos);
            numseñal=0;
            letseñal = String.valueOf(numseñal);
            textSeñal.setText(getResources().getString(R.string.señal)+ " " + letseñal + "%");
        }

        if (wifiManager.isWifiEnabled()) {
            mensajewifion();
        } else {
            mensajewifioff();
        }
    }

   /* @Override
    public void onBackPressed()
    {
        super.onBackPressed();



        //poner en marcha la publicidad al salir
        MainActivity_wifi_info.this.startAppAd.showAd();
        MainActivity_wifi_info.this.startAppAd.loadAd();


    }*/
    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
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

    public void reinicio(){
        Intent reinicio = getIntent();
        finish();
        startActivity(reinicio);
    }

    public void mensajewifion() {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.men_wifi_on),
                        Toast.LENGTH_SHORT);

        toast1.show();
    }
    public void mensajewifioff() {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.men_wifi_off),
                        Toast.LENGTH_SHORT);

        toast1.show();
    }

    public void mensajeactualizar() {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.actualizar),
                        Toast.LENGTH_SHORT);

        toast1.show();
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


                AlertDialog.Builder builder = new AlertDialog.Builder((Context)(this));
                builder.setMessage((CharSequence)(getResources().getString(R.string.toast_ayuda)))
                        .setIcon(R.mipmap.ic_launcher).setTitle((CharSequence) (getResources().getString(R.string.ayuda)))
                        .setCancelable(false).setNeutralButton((CharSequence) (getResources().getString(R.string.ok)),
                        (DialogInterface.OnClickListener) (new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.cancel();
                    }
                }));
                builder.create().show();




                return true;

            case R.id.votar:
                //boton votar
                Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=company.petron.imei");
                Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(intent);
                return true;
            case R.id.reiniciar:

                reinicio();

                mensajeactualizar();


                return true;
            case R.id.salir:
                //botón salir

                finish();


                return true;



            default:
                return super.onOptionsItemSelected(item);
    }
    }

}
