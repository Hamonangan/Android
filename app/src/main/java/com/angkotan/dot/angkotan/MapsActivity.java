package com.angkotan.dot.angkotan;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener, GoogleApiClient.ConnectionCallbacks,DirectionFinderListener,
        GoogleApiClient.OnConnectionFailedListener,Response.Listener,Response.ErrorListener{


        private double lat, lng;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Location location;
    private GoogleApiClient googleApiClient;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private TextView mTextView;
    public static final String REQUEST_TAG = "MainVolleyActivity";
    private RequestQueue mQueue;

    public static String[] ids;
    public static String[] angkot;


    public static final String JSON_ARRAY = "list_event";
    public static String KEY_ID = "id";
    public static String KEY_ANGKOT = "angkot";


String[][] arrayx = new String[][]{{"cililitan","arundina cibubur","1"},{"cililitan","pasar rebo","2"},{"cililitan","tamini","3"},{"cililitan","bambu apus","4"},{"pulogadung","kayu tinggi","5"},{"cililitan","ciracas","6"},{"cililitan","condet","7"},{"keong","cilangkap","8"},{"pasar rebo","jambore","9"},{"cililitan","pinang ranti","10"},{"pulogadung","rawa kuning","11"},{"pulogadung","lampiri","12"},{"rawamangun","pangkalan jati","13"},{"rawamangun","pulogebang","14"},{"rawamangun","transito","15"},{"pulogadung","pulogebang","16"},{"cililitan","kranji jakarta","17"},{"rawamangun","klender","18"},{"pulogadung","rorotan","19"},{"pulogadung","gempol","20"},{"kampung melayu","stasiun cakung","21"},{"kampung melayu","pondok kopi","22"},{"pulogadung","stasiun cakung","23"},{"cililitan","klender jakarta","24"},{"pulogadung","kalimalang","25"},{"kampung rambutan","pulogebang","26"},{"pulogadung","tamini","27"},{"kampung rambutan","pulogadung","28"},{"klender","cililitan","29"},{"kampung melayu","pulogadung","30"},{"cililitan","pulogadung","31"},{"kampung melayu","pondok gede","32"},{"kampung melayu","pondok kelapa jakarta","33"},{"bulak ringin","kampung rambutan","34"},{"terminal cililitan","munjul jakarta","35"},{"kampung melayu","gandaria","36"},{"kampung melayu","pulogadung","37"},{"kampung melayu","pisangan","38"},{"kampung rambutan","kalimalang","39"},{"pasar minggu","ciganjur","40"},{"cililitan","gandaria","41"},{"kampung rambutan","jatinegara","42"},{"rawamangun","kayumanis jakarta","43"},{"cililitan","rawasari jakarta","44"},{"cililitan","cipinang","45"},{"tamini","cilangkap","46"},{"kampung melayu","kampung rambutan","47"},{"cilincing","bendungan melayu jakarta","48"},{"kampung rambutan","pondok kelapa","49"},{"cililitan","jambore jakarta","50"},{"cililitan","kalisari","51"},{"cililitan","pondok bambu","52"},{"terminal cililitan","taman bunga","53"},{"kampung rambutan","cipinang","54"},{"pulogadung","kampung melayu","55"},{"cililitan","kampung melayu","56"},{"cililitan","mekarsari jakarta","57"},{"tamini","setu","58"},{"kampung melayu","duren sawit","59"},{"tamini","pondok gede","60"},{"kampung melayu","perumnas klender","61"},{"cililitan","duren sawit","62"},{"kampung rambutan","cawang","63"},{"cililitan","cibubur","64"},{"kampung rambutan","cililitan","65"},{"cililitan","jatinegara","66"},{"kalimalang","rawamangun","67"},{"pulogadung","harapan jaya","68"},{"cililitan","pekayon","69"},{"cililitan","cawang","70"},{"cililitan","cakung","71"},{"kampung rambutan","kampung melayu","72"},{"cililitan","cipayung","73"},{"terminal kampung melayu","kompleks pwi jakarta","74"},{"cililitan","keong","75"},{"kampung rambutan","klender","76"},{"terminal cililitan","setu jakarta","77"},{"kampung rambutan","cibubur","78"},{"rawamangun","cakung","79"},{"cililitan","cijantung","80"},{"rawamangun","kalimalang","81"},{"terminal cililitan","setu jakarta","82"},{"pondok labu","pasar kebayoran lama","83"},{"cililitan","cilangkap","84"},{"kampung rambutan","cakung","85"},{"cililitan","klapa dua wetan","86"},{"kampung rambutan","kota wisata","87"},{"cililitan","chandra","88"},{"tamini","bambu apus","89"},{"kampung rambutan","bj benteng","90"},{"cililitan","pondok rangon","91"},{"kampung rambutan","kramat jati","92"},{"kampung rambutan","condet","93"},{"kampung rambutan","tamini","94"},{"kampung rambutan","ciracas","95"},{"cililitan","kampung rambutan","96"},{"cililitan","condet","97"},{"cililitan","kramatjati","98"},{"kampung rambutan","bulak ringin","99"},{"kampung rambutan","duren sawit","100"},{"kampung rambutan","cipayung","101"},{"cililitan","komp halim","102"},{"kampung rambutan","pondok bambu","103"},{"kampung rambutan","arundina","104"},{"kampung rambutan","suci","105"},{"kampung rambutan","cipayung","106"},{"kampung rambutan","pondok kopi","107"},{"kalisari","cililitan","108"},{"kampung rambutan","matraman","109"},{"kampung rambutan","gandaria","110"},{"kampung rambutan","rawamangun","111"},{"rawamangun","jatinegara","112"},{"pinang ranti","kampung rambutan","113"},{"kampung rambutan","utan kayu","114"},{"pinang ranti","cawang","115"},{"kampung rambutan","pinang ranti","116"},{"pulogadung","kampung rambutan","117"},{"pulogadung","jatinegara","118"},{"pulogadung","matraman","119"},{"pulogadung","cipinang","120"},{"pulo gadung","rawamangun","121"},{"rawamangun","lebak bulus","122"},{"pulogadung","klender","123"},{"pulogadung","kramat jati","124"},{"kampung melayu","cibubur","125"},{"kampung melayu","gandaria","126"},{"kampung melayu","cipinang","127"},{"kampung melayu","rawamangun","128"},{"kampung melayu","jatinegara","129"},{"kampung melayu","kramat jati","130"},{"kampung rambutan","pulogadung","131"},{"rawamangun","pondok kopi","132"},{"kampung melayu","ciracas","133"},{"rawamangun","kampung rambutan","134"}};

    private String json;
String x="",y="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        setUpMapIfNeeded();
        Intent intent=getIntent();
String id="";
        if(intent !=null) {
            String origin = intent.getStringExtra("from2");
            String destination = intent.getStringExtra("to2");

            //if(origin.equals("cililitan") && destination.equals("pasar rebo")) id="2";

            x=origin;
            y=destination;
            Toast.makeText(MapsActivity.this, "Dari "+x+" Menuju "+y, Toast.LENGTH_LONG).show();



   /* for (int baris = 0; baris <= 133; baris++) {

        if (arrayx[baris][0].equals(x) && arrayx[baris][1].equals(y)) {
            id = Integer.parseInt(arrayx[baris][2]);
        } else {
            id = 135;
        }
    }

*/

            if(x.equals("cililitan") && y.equals("arundina cibubur")){id="1";}if(x.equals("cililitan") && y.equals("pasar rebo")){id="2";}if(x.equals("cililitan") && y.equals("tamini")){id="3";}if(x.equals("cililitan") && y.equals("bambu apus")){id="4";}if(x.equals("pulogadung") && y.equals("kayu tinggi")){id="5";}if(x.equals("cililitan") && y.equals("ciracas")){id="6";}if(x.equals("cililitan") && y.equals("condet")){id="7";}if(x.equals("keong") && y.equals("cilangkap")){id="8";}if(x.equals("pasar rebo") && y.equals("jambore")){id="9";}if(x.equals("cililitan") && y.equals("pinang ranti")){id="10";}if(x.equals("pulogadung") && y.equals("rawa kuning")){id="11";}if(x.equals("pulogadung") && y.equals("lampiri")){id="12";}if(x.equals("rawamangun") && y.equals("pangkalan jati")){id="13";}if(x.equals("rawamangun") && y.equals("pulogebang")){id="14";}if(x.equals("rawamangun") && y.equals("transito")){id="15";}if(x.equals("pulogadung") && y.equals("pulogebang")){id="16";}if(x.equals("cililitan") && y.equals("kranji jakarta")){id="17";}if(x.equals("rawamangun") && y.equals("klender")){id="18";}if(x.equals("pulogadung") && y.equals("rorotan")){id="19";}if(x.equals("pulogadung") && y.equals("gempol")){id="20";}if(x.equals("kampung melayu") && y.equals("stasiun cakung")){id="21";}if(x.equals("kampung melayu") && y.equals("pondok kopi")){id="22";}if(x.equals("pulogadung") && y.equals("stasiun cakung")){id="23";}if(x.equals("cililitan") && y.equals("klender jakarta")){id="24";}if(x.equals("pulogadung") && y.equals("kalimalang")){id="25";}if(x.equals("kampung rambutan") && y.equals("pulogebang")){id="26";}if(x.equals("pulogadung") && y.equals("tamini")){id="27";}if(x.equals("kampung rambutan") && y.equals("pulogadung")){id="28";}if(x.equals("klender") && y.equals("cililitan")){id="29";}if(x.equals("kampung melayu") && y.equals("pulogadung")){id="30";}if(x.equals("cililitan") && y.equals("pulogadung")){id="31";}if(x.equals("kampung melayu") && y.equals("pondok gede")){id="32";}if(x.equals("kampung melayu") && y.equals("pondok kelapa jakarta")){id="33";}if(x.equals("bulak ringin") && y.equals("kampung rambutan")){id="34";}if(x.equals("terminal cililitan") && y.equals("munjul jakarta")){id="35";}if(x.equals("kampung melayu") && y.equals("gandaria")){id="36";}if(x.equals("kampung melayu") && y.equals("pulogadung")){id="37";}if(x.equals("kampung melayu") && y.equals("pisangan")){id="38";}if(x.equals("kampung rambutan") && y.equals("kalimalang")){id="39";}if(x.equals("pasar minggu") && y.equals("ciganjur")){id="40";}if(x.equals("cililitan") && y.equals("gandaria")){id="41";}if(x.equals("kampung rambutan") && y.equals("jatinegara")){id="42";}if(x.equals("rawamangun") && y.equals("kayumanis jakarta")){id="43";}if(x.equals("cililitan") && y.equals("rawasari jakarta")){id="44";}if(x.equals("cililitan") && y.equals("cipinang")){id="45";}if(x.equals("tamini") && y.equals("cilangkap")){id="46";}if(x.equals("kampung melayu") && y.equals("kampung rambutan")){id="47";}if(x.equals("cilincing") && y.equals("bendungan melayu jakarta")){id="48";}if(x.equals("kampung rambutan") && y.equals("pondok kelapa")){id="49";}if(x.equals("cililitan") && y.equals("jambore jakarta")){id="50";}if(x.equals("cililitan") && y.equals("kalisari")){id="51";}if(x.equals("cililitan") && y.equals("pondok bambu")){id="52";}if(x.equals("terminal cililitan") && y.equals("taman bunga")){id="53";}if(x.equals("kampung rambutan") && y.equals("cipinang")){id="54";}if(x.equals("pulogadung") && y.equals("kampung melayu")){id="55";}if(x.equals("cililitan") && y.equals("kampung melayu")){id="56";}if(x.equals("cililitan") && y.equals("mekarsari jakarta")){id="57";}if(x.equals("tamini") && y.equals("setu")){id="58";}if(x.equals("kampung melayu") && y.equals("duren sawit")){id="59";}if(x.equals("tamini") && y.equals("pondok gede")){id="60";}if(x.equals("kampung melayu") && y.equals("perumnas klender")){id="61";}if(x.equals("cililitan") && y.equals("duren sawit")){id="62";}if(x.equals("kampung rambutan") && y.equals("cawang")){id="63";}if(x.equals("cililitan") && y.equals("cibubur")){id="64";}if(x.equals("kampung rambutan") && y.equals("cililitan")){id="65";}if(x.equals("cililitan") && y.equals("jatinegara")){id="66";}if(x.equals("kalimalang") && y.equals("rawamangun")){id="67";}if(x.equals("pulogadung") && y.equals("harapan jaya")){id="68";}if(x.equals("cililitan") && y.equals("pekayon")){id="69";}if(x.equals("cililitan") && y.equals("cawang")){id="70";}if(x.equals("cililitan") && y.equals("cakung")){id="71";}if(x.equals("kampung rambutan") && y.equals("kampung melayu")){id="72";}if(x.equals("cililitan") && y.equals("cipayung")){id="73";}if(x.equals("terminal kampung melayu") && y.equals("kompleks pwi jakarta")){id="74";}if(x.equals("cililitan") && y.equals("keong")){id="75";}if(x.equals("kampung rambutan") && y.equals("klender")){id="76";}if(x.equals("terminal cililitan") && y.equals("setu jakarta")){id="77";}if(x.equals("kampung rambutan") && y.equals("cibubur")){id="78";}if(x.equals("rawamangun") && y.equals("cakung")){id="79";}if(x.equals("cililitan") && y.equals("cijantung")){id="80";}if(x.equals("rawamangun") && y.equals("kalimalang")){id="81";}if(x.equals("terminal cililitan") && y.equals("setu jakarta")){id="82";}if(x.equals("pondok labu") && y.equals("pasar kebayoran lama")){id="83";}if(x.equals("cililitan") && y.equals("cilangkap")){id="84";}if(x.equals("kampung rambutan") && y.equals("cakung")){id="85";}if(x.equals("cililitan") && y.equals("klapa dua wetan")){id="86";}if(x.equals("kampung rambutan") && y.equals("kota wisata")){id="87";}if(x.equals("cililitan") && y.equals("chandra")){id="88";}if(x.equals("tamini") && y.equals("bambu apus")){id="89";}if(x.equals("kampung rambutan") && y.equals("bj benteng")){id="90";}if(x.equals("cililitan") && y.equals("pondok rangon")){id="91";}if(x.equals("kampung rambutan") && y.equals("kramat jati")){id="92";}if(x.equals("kampung rambutan") && y.equals("condet")){id="93";}if(x.equals("kampung rambutan") && y.equals("tamini")){id="94";}if(x.equals("kampung rambutan") && y.equals("ciracas")){id="95";}if(x.equals("cililitan") && y.equals("kampung rambutan")){id="96";}if(x.equals("cililitan") && y.equals("condet")){id="97";}if(x.equals("cililitan") && y.equals("kramatjati")){id="98";}if(x.equals("kampung rambutan") && y.equals("bulak ringin")){id="99";}if(x.equals("kampung rambutan") && y.equals("duren sawit")){id="100";}if(x.equals("kampung rambutan") && y.equals("cipayung")){id="101";}if(x.equals("cililitan") && y.equals("komp halim")){id="102";}if(x.equals("kampung rambutan") && y.equals("pondok bambu")){id="103";}if(x.equals("kampung rambutan") && y.equals("arundina")){id="104";}if(x.equals("kampung rambutan") && y.equals("suci")){id="105";}if(x.equals("kampung rambutan") && y.equals("cipayung")){id="106";}if(x.equals("kampung rambutan") && y.equals("pondok kopi")){id="107";}if(x.equals("kalisari") && y.equals("cililitan")){id="108";}if(x.equals("kampung rambutan") && y.equals("matraman")){id="109";}if(x.equals("kampung rambutan") && y.equals("gandaria")){id="110";}if(x.equals("kampung rambutan") && y.equals("rawamangun")){id="111";}if(x.equals("rawamangun") && y.equals("jatinegara")){id="112";}if(x.equals("pinang ranti") && y.equals("kampung rambutan")){id="113";}if(x.equals("kampung rambutan") && y.equals("utan kayu")){id="114";}if(x.equals("pinang ranti") && y.equals("cawang")){id="115";}if(x.equals("kampung rambutan") && y.equals("pinang ranti")){id="116";}if(x.equals("pulogadung") && y.equals("kampung rambutan")){id="117";}if(x.equals("pulogadung") && y.equals("jatinegara")){id="118";}if(x.equals("pulogadung") && y.equals("matraman")){id="119";}if(x.equals("pulogadung") && y.equals("cipinang")){id="120";}if(x.equals("pulo gadung") && y.equals("rawamangun")){id="121";}if(x.equals("rawamangun") && y.equals("lebak bulus")){id="122";}if(x.equals("pulogadung") && y.equals("klender")){id="123";}if(x.equals("pulogadung") && y.equals("kramat jati")){id="124";}if(x.equals("kampung melayu") && y.equals("cibubur")){id="125";}if(x.equals("kampung melayu") && y.equals("gandaria")){id="126";}if(x.equals("kampung melayu") && y.equals("cipinang")){id="127";}if(x.equals("kampung melayu") && y.equals("rawamangun")){id="128";}if(x.equals("kampung melayu") && y.equals("jatinegara")){id="129";}if(x.equals("kampung melayu") && y.equals("kramat jati")){id="130";}if(x.equals("kampung rambutan") && y.equals("pulogadung")){id="131";}if(x.equals("rawamangun") && y.equals("pondok kopi")){id="132";}if(x.equals("kampung melayu") && y.equals("ciracas")){id="133";}if(x.equals("rawamangun") && y.equals("kampung rambutan")){id="134";}if(x.equals("-") && y.equals("-")){id="135";}
            googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

            try {

                new DirectionFinder((DirectionFinderListener) this, origin, destination).execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            //get data json
            //String url = "http://192.168.43.244/angkot_json.php?type=rute&origin="+origin+"&destination="+destination;

            String url = "http://data-kendaraan.esy.es/angkot_json.php?type=id&id="+id;

            /*
            mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                    .getRequestQueue();

            final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET, url, new JSONObject(), this, (Response.ErrorListener) this);
            jsonRequest.setTag(REQUEST_TAG);

            mQueue.add(jsonRequest);
*/
            final TextView angkotview2=(TextView) findViewById(R.id.angkot);
            //Toast.makeText(MapsActivity.this,origin.toString().trim()+" "+destination.toString().trim(), Toast.LENGTH_LONG).show();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            showJSON(response);
                           // angkotview2.setText("RESPONSE "+response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MapsActivity.this, error.getMessage()+" Error", Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }




    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();


    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                Log.e("Sukses", "sukses");
            } else {
                Log.e("Error","Error");
            }
        }
    }



    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(-0.789275,113.921327)).title("Marker"));
        mMap.setOnMapClickListener(this);
        mMap.setMyLocationEnabled(true);

    }


    public void onMapClick(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latLng.latitude, latLng.longitude))
                        .title("Starting Point")
        );
        lat = latLng.latitude;
        lng = latLng.longitude;
    }


    public void onConnected(Bundle bundle) {
        if (location == null) {
            // get last location device
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (mMap != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
                mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                .title("Starting Point")

                );
                lat = location.getLatitude();
                lng = location.getLongitude();

                //Toast.makeText(this, "Lokasi kamu saat ini, sebagai patokan titik awal perjalanan kamu kak :')", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onDirectionFinderStart() {

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();


        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText("Waktu Tempuh : "+route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText("Jarak Tempuh : "+route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        TextView angkotview=(TextView) findViewById(R.id.angkot);
        angkotview.setText(error.getMessage());
    }

    @Override
    public void onResponse(Object response) {


        /*mTextView.setText("Response is: " + response);
        try {
            mTextView.setText(mTextView.getText() + "\n\n" + ((JSONObject) response).getString
                    ("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        /*
        String dataangkot = "";
        try {

            JSONObject jsonRootObject=(JSONObject) response;

            JSONArray jsonArray=jsonRootObject.optJSONArray("list_event");

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                String name=jsonObject.getString("angkot").toString();

                dataangkot+=name;

            }
            angkotview.setText(dataangkot);

        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            angkotview.setText(e.toString());
        }
        */





    }

    private void showJSON(String json){
        TextView angkotview=(TextView) findViewById(R.id.angkot);
        TextView ruteview=(TextView) findViewById(R.id.ruteID);

        String kodeangkot="";
        String koderute="";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);



            //emails = new String[users.length()];


            JSONObject collegeData = users.getJSONObject(0);
            kodeangkot=collegeData.getString("angkot");
            koderute=collegeData.getString("rute");


        } catch (JSONException e) {
            e.printStackTrace();
            angkotview.setText("Error "+e.getMessage());
        }
        angkotview.setText("Angkot : "+kodeangkot);
        ruteview.setText("Rute : \n"+koderute);
    }


}
