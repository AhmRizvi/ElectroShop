package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electroshop.R;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Customer;
import com.example.electroshop.model.Product;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView newaccountCREATE, homeviewLINK;
    private EditText cusEMAIL, cusPASSWORD;
    Button loginButton;
    TextInputLayout setmessege;
    Customer customerLogin;
    DatabaseHelper databaseHelper;

    SharedPreferences setsharedpreferences;
    SharedPreferences getsharedpreferences;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        idcasting();
        databaseHelper = new DatabaseHelper(this);
        newaccountCREATE.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        homeviewLINK.setOnClickListener(this);


    }


    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.customerLogin:
                prodinsert();
                try {
                    //todo insert product

                    customerLogin = databaseHelper.customerlogin(cusEMAIL.getText().toString(), cusPASSWORD.getText().toString());
                    if (customerLogin != null) {
                        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_SHORT).show();


                        setsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = setsharedpreferences.edit();
                        editor.putString("Email", customerLogin.getCusemail());
                        editor.commit();


                        getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
                        email = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
                        Log.d("TAG", "User login email: " + email);
                        startActivity(new Intent(getBaseContext(),HomeActivity.class));

                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "lInvalid Email and Password", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.newaccountcreate:
                prodinsert();
                startActivity(new Intent(getBaseContext(), CreateCustomerAccount.class));
                break;
            case R.id.homeviewlink:
                prodinsert();
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                break;


        }

    }


    private void idcasting() {
        setmessege = (TextInputLayout) findViewById(R.id.cusemaillayout);

        cusEMAIL = (EditText) findViewById(R.id.cusname);
        cusPASSWORD = (EditText) findViewById(R.id.cuspassword);
        newaccountCREATE = findViewById(R.id.newaccountcreate);
        loginButton = (Button) findViewById(R.id.customerLogin);
        homeviewLINK = (TextView) findViewById(R.id.homeviewlink);

    }


    public void prodinsert() {
        try {

            databaseHelper.insetProduct(new Product("Asus Rog Strix G513IH-HN006 15.6´´ R7-4800H/16GB/512GB SSD/GTX 1650 4GB Gaming Laptop",
                    "Laptop",
                    85400.99,
                    "Asus", "Powerful AMD Ryzen™ 9 5900HX CPU and GeForce RTX™ 3070 GPU,- No OS\n" +
                    " AMD Ryzen 7 4800H (8MB Cache, 2.9GHz)," +
                    " 39.6 cm (15.6´´) Full HD 1920 x 1080 IPS, NVIDIA GeForce GTX 1650 (4GB GDDR6)" +
                    " 16GB (3200MHz) DDR4-SDRAM (2 x 16) & 512GB SSD", 45,
                    "https://www.techinn.com/f/13822/138228220/asus-rog-strix-g513ih-hn006-15.6-r7-4800h-16gb-512gb-ssd-gtx-1650-4gb-gaming-laptop.jpg"));

            databaseHelper.insetProduct(new Product("Apple MacBook Air 13 ff´´ M1/8GB/256GB SSD Laptop",
                    "Laptop", 86966.99, "Apple", "" +
                    "built 5-Nanometer process technology ColorGrey\n" +

                    "Product AspectNew",
                    35,
                    "https://www.techinn.com/f/13787/137870845/apple-macbook-air-13-m1-8gb-256gb-ssd-laptop.jpg"));

            databaseHelper.insetProduct(new Product("Apple MacBook Air 13´´ M1/8GB/256GB SSD Laptop",
                    "Laptop",
                    86966.99,
                    "Apple",
                    "" +
                            "built 5-Nanometer process technology ColorGrey\n" +
                            "Recommended useDomestic\n" +
                            "Type of laptopsUltrabooks\n",

                    65,
                    "https://www.techinn.com/f/13787/137870845/apple-macbook-air-13-m1-8gb-256gb-ssd-laptop.jpg"));

            databaseHelper.insetProduct(new Product("Lenovo ThinkPad X1 Carbon Gen 9 14´´ i5-1135G7/8GB/256GB SSD Laptop",
                    "Laptop",
                    128448.49,

                    "Lenovo",
                    "ThinkPad X1 Carbon Gen 9 laptop ColorBlack\n" +
                            "Recommended useProfesional\n" +
                            "Type of laptopsUltrabooks\n" +
                            "SSD Storage256 GB\n" +
                            "RAM8 GB\n" +

                            "Product AspectNew",
                    75,
                    "https://www.techinn.com/f/13822/138225108/lenovo-thinkpad-x1-carbon-gen-9-14-i5-1135g7-8gb-256gb-ssd-laptop.jpg"));

            databaseHelper.insetProduct(new Product("Lenovo ThinkPad X12 G1 13´´ i5-1130G7/16GB/512GB SSD Laptop",
                    "Laptop",
                    149669.99,
                    "Lenovo",
                    "Intel Core i5-1130G7 (4C/8T, 1.8/4.0GHz, 8MB)\n" +
                            " 16GB LPDDR4x-4266\n" +
                            " 512GB SSD M.2 2242 PCIe 3.0x4 NVMe\n" +
                            " Intel Iris Xe Graphics\n" +
                            " Windows 10 Pro 64",

                    105,
                    "https://www.techinn.com/f/13851/138514336/lenovo-thinkpad-x12-g1-13-i5-1130g7-16gb-512gb-ssd-laptop.jpg"));
            databaseHelper.insetProduct(new Product("HP 252 G8 15.6´´ i5-1135G7/8GB/256GB SSD Laptop",
                    "Laptop",
                    52890.99,
                    "Hp",
                    "ColorSilver\n" +
                            "Recommended useDomestic\n" +
                            "Type of laptopsStandard\n" +
                            "SSD Storage256 GB\n",
                    105,
                    "https://www.techinn.com/f/13787/137870843/apple-macbook-air-13-m1-8gb-256gb-ssd-laptop.jpg"));
            databaseHelper.insetProduct(new Product("Asus ExpertBook 15.6´´ i3-1115G4/8GB/256GB SSD Laptop",
                    "Laptop",
                    50159.49,
                    "Asus",
                    "ASUS P1511 is the entry-level laptop" +
                            "ColorSilver\n" +
                            "Type of laptopsworkstation\n" +
                            "SSD Storage256 GB\n" +
                            "RAM8 GB\n",
                    105,
                    "https://www.techinn.com/f/13861/138613116/asus-expertbook-15.6-i3-1115g4-8gb-256gb-ssd-laptop.jpg"));


            ///////////////////////////////////////////////////////////////////////desktop///////////////////////////////////////

            databaseHelper.insetProduct(new Product("Apple iMac Retina 4.5K 24´´ M1 8CPU-8GPU/8GB/512GB SSD All In One PC",
                    "Desktop",
                    50159.49,
                    "Apple",
                    "Features:\n" +
                            "\n" +
                            "- Apple M1‑chip\n" +
                            "Hard disc typeSSD\n" +
                            "Product AspectNew",
                    85,
                    "https://www.techinn.com/f/13809/138091980/apple-imac-retina-4.5k-24-m1-8cpu-8gpu-8gb-512gb-ssd-all-in-one-pc.jpg"));


            databaseHelper.insetProduct(new Product("Gigabyte Aorus GB-AMXI9N8A-2051 Gaming Desktop PC",
                    "Desktop",
                    276120.99,
                    "Gigabyte",
                    "Specifications:\n" +
                            "\n" +
                            "- Processor frequency: 3.7 GHz\n" +
                            "- Processor family: AMD Ryzen 9\n" +


                            "Hard disc typeSSD\n" +
                            "Product AspectNew",
                    85,
                    "https://www.techinn.com/f/13856/138567951/gigabyte-aorus-gb-amxi9n8a-2051-i9-11900k-16gb-3tb-ssd-nvidia-geforce-rtx-3080-10gb-gaming-desktop-pc.jpg"));

            databaseHelper.insetProduct(new Product("Lenovo V30a 24IML 11FT000PSP 23.8´´",
                    "Desktop",
                    51557.99,
                    "Lenovo",
                    "10th Gen Intel® Core i5 processors" +
                            "olorBlack\n" +

                            "Hard disc typeSSD",
                    65,
                    "https://www.techinn.com/f/13814/138148187/lenovo-v30a-24iml-11ft000psp-23.8-i3-10110u-8gb-256gb-ssd-all-in-one-pc.jpg"));
            databaseHelper.insetProduct(new Product("Dell Optiplex 7780 27´´ i5-10105/8GB/256GB SSD/UHD Graphics 630 All In One PC",
                    "Desktop",
                    114118.99,
                    "Lenovo",
                    "Specifications:\n" +
                            "\n" +
                            "Display:\n" +
                            "- Display diagonal: 68.6 cm (27´´)\n",

                    5,
                    "https://www.techinn.com/f/13848/138482594/dell-optiplex-7780-27-i5-10105-8gb-256gb-ssd-uhd-graphics-630-all-in-one-pc.jpg"));

            databaseHelper.insetProduct(new Product("MSI Pro 24X 10M-044EU 23.8´´ ",
                    "Desktop",
                    58614.49,
                    "Lenovo",
                    "Specifications: Pro 24x Series Aio Pc Has An Ultra-Slim Design At An Incredibly Slim 6.5mm" +
                            "ColorBlack\n" +

                            "Product AspectNew",
                    25,
                    "https://www.techinn.com/f/13780/137806644/msi-pro-24x-10m-044eu-23.8-6405ucmu-4gb-64gb-ssd-all-in-one-pc.jpg"));


            ////////////////////////////////////////////////////////////////////accessories////////////////////////////////////////////////


            databaseHelper.insetProduct(new Product("Scorpion Marvo M518 RGB Wireless Mouse´",
                    "Accessories",
                    1740.99,
                    "Scorpion",
                    "Scorpion MA-M518. Form factor: Ambidextrous. Movement detection technology: Optical, Device interface",

                    35,
                    "https://www.techinn.com/f/13861/138610845/scorpion-marvo-m518-rgb-wireless-mouse.jpg"));


            databaseHelper.insetProduct(new Product("Trust GXT 220 Kuzo Cooling Base 17.3´",
                    "Accessories",
                    2031.49,
                    "Trust",
                    "Keeps your notebook cool to increase\n" +
                            "- large red illuminated fan\n" +

                            "- Total weight 484 g\n" +
                            "Connectivity:",
                    25,
                    "https://www.techinn.com/f/13818/138181855/trust-gxt-220-kuzo-cooling-base-17.3.jpg"));

            databaseHelper.insetProduct(new Product("Genesis OXID 250 17.3´´ Laptop Cooling Base´",
                    "Accessories",
                    2049,
                    "Trust",
                    "Efficient Fans\n" +
                            "- Led Backlight\n" +
                            "- Usb Hub\n" + " " +
                            " up to the highest level.",
                    25,
                    "https://www.techinn.com/f/13818/138181855/trust-gxt-220-kuzo-cooling-base-17.3.jpg"));

            databaseHelper.insetProduct(new Product("Approx Mini Laptop Fan 17´",
                    "Accessories",
                    367.99,
                    "Trust",
                    "Maximum screen size: 43.2 cm (17´´)\n" +

                            "- Dimensions (WxDxH): 75 x 60 x 10 mm",
                    45,
                    "https://www.techinn.com/f/13818/138181855/trust-gxt-220-kuzo-cooling-base-17.3.jpg"));

            databaseHelper.insetProduct(new Product("Compulocks MBALDG02 MackBook Air Lock Adapter´",
                    "Accessories",
                    2940.99,
                    "Compulocks",
                    "Product description Compulocks MBALDG02 MackBook Air Lock Adapter\n" +
                            "Compulocks Macbook Air Lock Adapter. Product Colour: Silver, Best Uses: Notebook. Weight: 226.8 G\n" +
                            "\n" +
                            "\n" +

                            "Product TypePadlocks",
                    45,
                    "https://www.techinn.com/f/13814/138148338/compulocks-mbaldg02-mackbook-air-lock-adapter.jpg"));

            databaseHelper.insetProduct(new Product("Canyon Sulaco Mouse´",
                    "Accessories",
                    1572.49,
                    "Canyon",
                    "Features:\n" +
                            "• High-quality optical sensor PixArt 3519\n" +
                            "• Adjustable DPI settings 800/1200/2400/3200/4200\n" +
                            "• 7 programmable buttons\n" +

                            "• Scan frequency: 3000FPS",
                    45,
                    "https://www.techinn.com/f/13801/138018775_4/canyon-sulaco-mouse.jpg"));


        } catch (Exception e) {

        }

    }


}