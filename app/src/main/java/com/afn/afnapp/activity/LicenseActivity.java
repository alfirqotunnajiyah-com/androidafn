package com.afn.afnapp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LicenseActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private TextView tvValue;

    private ProgressDialog loading;
    private RequestQueue queue;
    private ApiUrl apiUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        queue = Volley.newRequestQueue(this);
        apiUrl = new ApiUrl();
        loading = new ProgressDialog(this);

        initView();
        retrieveData();
    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvValue = (TextView) findViewById(R.id.tvValue);
    }

    void retrieveData() {
        String[] lisensiIcon = {
                "1. Icon Al Quran\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"                 title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\"                 title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "2. Icon Home\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/iconnice\" title=\"Iconnice\">Iconnice</a> from <a href=\"https://www.flaticon.com/\" \t\t\t    title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" \t\t\t    title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "3. Icon Feed\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.freepik.com/\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" \t\t\t    title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" \t\t\t    title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "4. Icon Profile\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/srip\" title=\"srip\">srip</a> from <a href=\"https://www.flaticon.com/\" \t\t\t    title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" \t\t\t    title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "\n" +
                        "5. Instagram\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"             title=\"Flaticon\">www.flaticon.com</a></div>\n" +
                        "\n" +
                        "6. Facebook\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"             title=\"Flaticon\">www.flaticon.com</a></div>\n" +
                        "\n" +
                        "7. Twitter\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"             title=\"Flaticon\">www.flaticon.com</a></div>\n" +
                        "\n" +
                        "8. Youtube \n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"             title=\"Flaticon\">www.flaticon.com</a></div>\n" +
                        "\n" +
                        "9. Telegram\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"             title=\"Flaticon\">www.flaticon.com</a></div>\n" +
                        "\n" +
                        "10. Pagi\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/photo3idea-studio\" title=\"photo3idea_studio\">photo3idea_studio</a> from <a href=\"https://www.flaticon.com/\" \t\t\t    title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" \t\t\t    title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "11. Petang\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/photo3idea-studio\" title=\"photo3idea_studio\">photo3idea_studio</a> from <a href=\"https://www.flaticon.com/\" \t\t\t    title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" \t\t\t    title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "12. Play,Download,Pause and listDraw\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/appzgear\" title=\"Appzgear\">Appzgear</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" \t\t\t    title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "13. Masjid\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"                 title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\"                 title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "14. Calendar Hijri\n" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"                 title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\"                 title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n" +
                        "\n" +
                        "15. Open Book (Kajian)" +
                        "\n" +
                        "<div>Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a></div>"
        };

        Spanned result = HtmlCompat.fromHtml(lisensiIcon[0], Html.FROM_HTML_MODE_LEGACY);
        tvValue.setText(result);
        tvValue.setMovementMethod(LinkMovementMethod.getInstance());
        //Linkify.addLinks(tvValue, Linkify.WEB_URLS);
    }
}
