package company.petron.wifiinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    private String infoIP, infoSSID, infoBSSID,
            infoSSIDOculto, infoEstado, infoRSSID,
            infoMAC, infoVelocidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_wifi_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
                infoSSIDOculto = "SID oculto: No";
            else
                infoSSIDOculto = "SID oculto: Sí";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_wifi_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
