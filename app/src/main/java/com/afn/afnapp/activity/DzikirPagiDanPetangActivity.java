package com.afn.afnapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.afn.afnapp.R;
import com.afn.afnapp.fragment.FragmentIsiDzikirActivity;

import java.util.ArrayList;

public class DzikirPagiDanPetangActivity extends AppCompatActivity {

    private android.support.v4.view.ViewPager vPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ArrayList<FragmentIsiDzikirActivity> fragments = new ArrayList<>();

    private String[] judulDzikir = {
            "Ta'awudz (Dibaca 1x)",
            "Ayat Kursi (Dibaca 1x)",
            "Surat Al-Ikhlas (Dibaca 3x)",
            "Surat Al-Falaq (Dibaca 3x)",
            "Surat An-Nas (Dibaca 3x)",
            "Dibaca 1x",
            "Dibaca 1x",
            "Sayyidul Istighfar (Dibaca 1x)",
            "Dibaca 3x",
            "Dibaca 1x",
            "Dibaca 1x",
            "Dibaca 3x",
            "Dibaca 3x",
            "Dibaca 1x",
            "Dibaca 1x",
            "Dibaca 10x atau 1x",
            "Dibaca 100x",
            "Dibaca 3x",
            "Dibaca 1x",
            "Dibaca 100x",
            "Dibaca setiap hari 100x"
    };

    private String[] isiBacaanDzikir = {
            "أَعُوذُ بِاللَّهِ مِنْ الشَّيْطَانِ الرَّجِيمِ",

            "اللَّهُ لاَ إِلَهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ، لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ، لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ، مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ، يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ، وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلاَّ بِمَا شَاءَ، وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ، وَلَا يَئُودُهُ حِفْظُهُمَا، وَهُوَ الْعَلِيُّ الْعَظِيم",

            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n" +
                    "قُلْ هُوَ اللَّهُ أَحَدٌ. اللَّهُ الصَّمَدُ. لَمْ يَلِدْ وَلَمْ يُولَدْ. وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",

            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n" +
                    "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ. مِن شَرِّ مَا خَلَقَ. وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ. وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ. وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ",

            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n" +
                    "قُلْ أَعُوذُ بِرَبِّ النَّاسِ. مَلِكِ النَّاسِ. إِلَهِ النَّاسِ. مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ. الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ. مِنَ الْجِنَّةِ وَ النَّاسِ",

            "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لاَ إِلَـهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيْرُ. رَبِّ أَسْأَلُكَ خَيْرَ مَا فِيْ هَذَا الْيَوْمِ وَخَيْرَ مَا بَعْدَهُ، وَأَعُوْذُ بِكَ مِنْ شَرِّ مَا فِيْ هَذَا الْيَوْمِ وَشَرِّ مَا بَعْدَهُ، رَبِّ أَعُوْذُ بِكَ مِنَ الْكَسَلِ وَسُوْءِ الْكِبَرِ، رَبِّ أَعُوْذُ بِكَ مِنْ عَذَابٍ فِي النَّارِ وَعَذَابٍ فِي الْقَبْرِ",

            "اَللَّهُمَّ بِكَ أَصْبَحْنَا، وَبِكَ أَمْسَيْنَا، وَبِكَ نَحْيَا، وَبِكَ نَمُوْتُ وَإِلَيْكَ النُّشُوْرُ",

            "اَللَّهُمَّ أَنْتَ رَبِّيْ لاَ إِلَـهَ إِلاَّ أَنْتَ، خَلَقْتَنِيْ وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوْذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوْءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوْءُ بِذَنْبِيْ فَاغْفِرْ لِيْ فَإِنَّهُ لاَ يَغْفِرُ الذُّنُوْبَ إِلاَّ أَنْتَ",

            "اَللَّهُمَّ عَافِنِيْ فِيْ بَدَنِيْ، اَللَّهُمَّ عَافِنِيْ فِيْ سَمْعِيْ، اَللَّهُمَّ عَافِنِيْ فِيْ بَصَرِيْ، لاَ إِلَـهَ إِلاَّ أَنْتَ. اَللَّهُمَّ إِنِّي أَعُوْذُ بِكَ مِنَ الْكُفْرِ وَالْفَقْرِ، وَأَعُوْذُ بِكَ مِنْ عَذَابِ الْقَبْرِ، لاَ إِلَـهَ إِلاَّ أَنْتَ",

            "اَللَّهُمَّ إِنِّيْ أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي الدُّنْيَا وَاْلآخِرَةِ، اَللَّهُمَّ إِنِّيْ أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي دِيْنِيْ وَدُنْيَايَ وَأَهْلِيْ وَمَالِيْ اللَّهُمَّ اسْتُرْ عَوْرَاتِى وَآمِنْ رَوْعَاتِى. اَللَّهُمَّ احْفَظْنِيْ مِنْ بَيْنِ يَدَيَّ، وَمِنْ خَلْفِيْ، وَعَنْ يَمِيْنِيْ وَعَنْ شِمَالِيْ، وَمِنْ فَوْقِيْ، وَأَعُوْذُ بِعَظَمَتِكَ أَنْ أُغْتَالَ مِنْ تَحْتِيْ",

            "اَللَّهُمَّ عَالِمَ الْغَيْبِ وَالشَّهَادَةِ فَاطِرَ السَّمَاوَاتِ وَاْلأَرْضِ، رَبَّ كُلِّ شَيْءٍ وَمَلِيْكَهُ، أَشْهَدُ أَنْ لاَ إِلَـهَ إِلاَّ أَنْتَ، أَعُوْذُ بِكَ مِنْ شَرِّ نَفْسِيْ، وَمِنْ شَرِّ الشَّيْطَانِ وَشِرْكِهِ، وَأَنْ أَقْتَرِفَ عَلَى نَفْسِيْ سُوْءًا أَوْ أَجُرُّهُ إِلَى مُسْلِمٍ",

            "بِسْمِ اللهِ الَّذِي لاَ يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي اْلأَرْضِ وَلاَ فِي السَّمَاءِ وَهُوَ السَّمِيْعُ الْعَلِيْمُ",

            "رَضِيْتُ بِاللهِ رَبًّا، وَبِاْلإِسْلاَمِ دِيْنًا، وَبِمُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ نَبِيًّا",

            "يَا حَيُّ يَا قَيُّوْمُ بِرَحْمَتِكَ أَسْتَغِيْثُ، أَصْلِحْ لِيْ شَأْنِيْ كُلَّهُ وَلاَ تَكِلْنِيْ إِلَى نَفْسِيْ طَرْفَةَ عَيْنٍ",

            "أَصْبَحْنَا عَلَى فِطْرَةِ اْلإِسْلاَمِ وَعَلَى كَلِمَةِ اْلإِخْلاَصِ، وَعَلَى دِيْنِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِيْنَا إِبْرَاهِيْمَ، حَنِيْفًا مُسْلِمًا وَمَا كَانَ مِنَ الْمُشْرِكِيْنَ",

            "لاَ إِلَـهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيْرُ",

            "لاَ إِلَـهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيْرُ",

            "سُبْحَانَ اللهِ وَبِحَمْدِهِ: عَدَدَ خَلْقِهِ، وَرِضَا نَفْسِهِ، وَزِنَةَ عَرْشِهِ وَمِدَادَ كَلِمَاتِهِ",

            "اَللَّهُمَّ إِنِّيْ أَسْأَلُكَ عِلْمًا نَافِعًا، وَرِزْقًا طَيِّبًا، وَعَمَلاً مُتَقَبَّلاً",

            "سُبْحَانَ اللهِ وَبِحَمْدِهِ",

            "أَسْتَغْفِرُ اللهَ وَأَتُوْبُ إِلَيْهِ"
    };

    private String[] isiTerjemahanDzikir = {
            "“Aku berlindung kepada Allah dari godaan syaitan yang terkutuk.”",

            "“Allah tidak ada Ilah (yang berhak diibadahi) melainkan Dia Yang Hidup Kekal lagi terus menerus mengurus (makhluk-Nya); tidak mengantuk dan tidak tidur. Kepunyaan-Nya apa yang ada di langit dan di bumi. Tidak ada yang dapat memberi syafa’at di sisi Allah tanpa izin-Nya. Allah mengetahui apa-apa yang (berada) dihadapan mereka, dan dibelakang mereka dan mereka tidak mengetahui apa-apa dari Ilmu Allah melainkan apa yang dikehendaki-Nya. Kursi Allah meliputi langit dan bumi. Dan Allah tidak merasa berat memelihara keduanya, Allah Mahatinggi lagi Mahabesar.” (QS. Al-Baqarah: 255)",

            "“Katakanlah, Dia-lah Allah Yang Maha Esa. Allah adalah (Rabb) yang segala sesuatu bergantung kepada-Nya. Dia tidak beranak dan tidak pula diperanakkan. Dan tidak ada seorang pun yang setara dengan-Nya.’” (QS. Al-Ikhlash: 1-4).",

            "“Katakanlah: ‘Aku berlindung kepada Rabb Yang menguasai (waktu) Shubuh dari kejahatan makhluk-Nya. Dan dari kejahatan malam apabila telah gelap gulita. Dan dari kejahatan wanita-wanita tukang sihir yang menghembus pada buhul-buhul. Serta dari kejahatan orang yang dengki apabila dia dengki.”‘ (QS. Al-Falaq: 1-5).",

            "”Katakanlah, ‘Aku berlindung kepada Rabb (yang memelihara dan menguasai) manusia. Raja manusia. Sembahan (Ilah) manusia. Dari kejahatan (bisikan) syaitan yang biasa bersembunyi. Yang membisikkan (kejahatan) ke dalam dada-dada manusia. Dari golongan jin dan manusia.’” (QS. An-Naas: 1-6).",

            "”Kami telah memasuki waktu pagi dan kerajaan hanya milik Allah, segala puji hanya milik Allah. Tidak ada ilah yang berhak diibadahi dengan benar kecuali Allah Yang Maha Esa, tiada sekutu bagi-Nya. Bagi-Nya kerajaan dan bagi-Nya pujian. Dia-lah Yang Mahakuasa atas segala sesuatu. Wahai Rabb, aku mohon kepada-Mu kebaikan di hari ini dan kebaikan sesudahnya. Aku berlindung kepada-Mu dari kejahatan hari ini dan kejahatan sesudahnya. Wahai Rabb, aku berlindung kepada-Mu dari kemalasan dan kejelekan di hari tua. Wahai Rabb, aku berlindung kepada-Mu dari siksaan di Neraka dan siksaan di kubur.”",

            "“Ya Allah, dengan rahmat dan pertolongan-Mu kami memasuki waktu pagi, dan dengan rahmat dan pertolongan-Mu kami memasuki waktu sore. Dengan rahmat dan kehendak-Mu kami hidup dan dengan rahmat dan kehendak-Mu kami mati. Dan kepada-Mu kebangkitan (bagi semua makhluk).”",

            "“Ya Allah, Engkau adalah Rabb-ku, tidak ada Ilah (yang berhak diibadahi dengan benar) kecuali Engkau, Engkau-lah yang menciptakanku. Aku adalah hamba-Mu. Aku akan setia pada perjanjianku dengan-Mu semampuku. Aku berlindung kepada-Mu dari kejelekan (apa) yang kuperbuat. Aku mengakui nikmat-Mu (yang diberikan) kepadaku dan aku mengakui dosaku, oleh karena itu, ampunilah aku. Sesungguhnya tidak ada yang dapat mengampuni dosa kecuali Engkau.”",

            "“Ya Allah, selamatkanlah tubuhku (dari penyakit dan dari apa yang tidak aku inginkan). Ya Allah, selamatkanlah pendengaranku (dari penyakit dan maksiat atau dari apa yang tidak aku inginkan). Ya Allah, selamatkanlah penglihatanku, tidak ada Ilah yang berhak diibadahi dengan benar kecuali Engkau. Ya Allah, sesungguhnya aku berlindung kepada-Mu dari kekufuran dan kefakiran. Aku berlindung kepada-Mu dari siksa kubur, tidak ada Ilah yang berhak diibadahi dengan benar kecuali Engkau.”",

            "“Ya Allah, sesungguhnya aku memohon kebajikan dan keselamatan di dunia dan akhirat. Ya Allah, sesungguhnya aku memohon kebajikan dan keselamatan dalam agama, dunia, keluarga dan hartaku. Ya Allah, tutupilah auratku (aib dan sesuatu yang tidak layak dilihat orang) dan tentramkan-lah aku dari rasa takut. Ya Allah, peliharalah aku dari depan, belakang, kanan, kiri dan dari atasku. Aku berlindung dengan kebesaran-Mu, agar aku tidak disambar dari bawahku (aku berlindung dari dibenamkan ke dalam bumi).”",

            "“Ya Allah Yang Mahamengetahui yang ghaib dan yang nyata, wahai Rabb Pencipta langit dan bumi, Rabb atas segala sesuatu dan Yang Merajainya. Aku bersaksi bahwa tidak ada Ilah yang berhak diibadahi dengan benar kecuali Engkau. Aku berlindung kepada-Mu dari kejahatan diriku, syaitan dan ajakannya menyekutukan Allah (aku berlindung kepada-Mu) dari berbuat kejelekan atas diriku atau mendorong seorang muslim kepadanya.”",

            "“Dengan Menyebut Nama Allah, yang dengan Nama-Nya tidak ada satupun yang membahayakan, baik di bumi maupun dilangit. Dia-lah Yang Mahamendengar dan Maha mengetahui.”",

            "“Aku rela (ridha) Allah sebagai Rabb-ku (untukku dan orang lain), Islam sebagai agamaku dan Muhammad صلي الله عليه وسلم sebagai Nabiku (yang diutus oleh Allah).”",

            "“Wahai Rabb Yang Maha hidup, Wahai Rabb Yang Maha berdiri sendiri (tidak butuh segala sesuatu) dengan rahmat-Mu aku meminta pertolongan, perbaikilah segala urusanku dan jangan diserahkan (urusanku) kepada diriku sendiri meskipun hanya sekejap mata (tanpa mendapat pertolongan dari-Mu).”",

            "“Di waktu pagi kami berada diatas fitrah agama Islam, kalimat ikhlas, agama Nabi kami Muhammad صلي الله عليه وسلم dan agama ayah kami, Ibrahim, yang berdiri di atas jalan yang lurus, muslim dan tidak tergolong orang-orang musyrik.”",

            "“Tidak ada Ilah yang berhak diibadahi dengan benar selain Allah Yang Maha Esa, tidak ada sekutu bagi-Nya. Bagi-Nya kerajaan dan bagi-Nya segala puji. Dan Dia Mahakuasa atas segala sesuatu.”",

            "“Tidak ada Ilah yang berhak diibadahi dengan benar selain Allah Yang Maha Esa, tidak ada sekutu bagi-Nya. Bagi-Nya kerajaan dan bagi-Nya segala puji. Dan Dia Maha kuasa atas segala sesuatu.”",

            "“Mahasuci Allah, aku memuji-Nya sebanyak bilangan makhluk-Nya, Mahasuci Allah sesuai ke-ridhaan-Nya, Mahasuci seberat timbangan ‘Arsy-Nya, dan Mahasuci sebanyak tinta (yang menulis) kalimat-Nya.”",

            "“Ya Allah, sesungguhnya aku meminta kepada-Mu ilmu yang bermanfaat, rizki yang halal, dan amalan yang diterima.”",

            "“Mahasuci Allah, aku memuji-Nya.”",

            "“Aku memohon ampunan kepada Allah dan bertaubat kepada-Nya.”"
    };

    private String[] isiFaidahDanDalil = {
            "",

            "“Barangsiapa yang membaca ayat ini ketika pagi hari, maka ia dilindungi dari (gangguan) jin hingga sore hari. Dan barangsiapa mengucapkannya ketika sore hari, maka ia dilindungi dari (gangguan) jin hingga pagi hari.” (Lihat Mustadrak Al-Hakim 1/562, Shahiih at-Targhiib wat Tarhiib 1/418 no. 662, shahih).",

            "HR. Abu Dawud no. 5082, an-Nasa-i VIII/250 dan at-Tirmidzi no. 3575, Ahmad V/312, Shahiih at-Tirmidzi no. 2829, Tuhfatul Ahwadzi no. 3646, Shahiih at-Targhiib wat Tarhiib 1/411 no. 649, hasan shahih",

            "Ibid.",

            "“Barangsiapa membaca tiga surat tersebut setiap pagi dan sore hari, maka (tiga surat tersebut) cukup baginya dari segala sesuatu”. Yakni mencegahnya dari berbagai kejahatan. ( HR. Abu Dawud no. 5082, Shahiih Abu Dawud no. 4241, Annasa-i VIII 250 dan At-Tirmizi no. 3575 , At-Tarmidzi berkata “Hadits ini hasan shahih” Ahmad V/312, dari Abdullah bin Khubaib radhiyallahu ‘anhu. Shahiih at-Tirmidzi no. 2829, Tuhfatul Ahwadzi no. 3646, Shahiih at-Targhiib wat Tarhiib 1/411 no. 649, hasan shahih).",

            "HR. Muslim no. 2723 (75), Abu Dawud no. 5071, dan at-Tirmidzi 3390, shahih dari Abdullah Ibnu Mas’ud.",

            "HR. Al-Bukhari dalam al-Adabul Mufrad no. 1199, lafazh ini adalah lafazh al-Bukhari, at-Tirmidzi no. 3391, Abu Dawud no. 5068, Ahmad 11/354, Ibnu Majah no. 3868, Dari Abu Hurairah Radhiyallahu ‘anhu. Shahiih al-Adabil Mufrad no. 911, shahih. Lihat pula Silsilah al-Ahaadiits ash-Shahiihah no. 262.",

            "“Barangsiapa membacanya dengan yakin di waktu pagi lalu ia meninggal sebelum masuk waktu sore, maka ia termasuk ahli Surga. Dan barangsiapa membacanya dengan yakin di waktu sore lalu ia meninggal sebelum masuk waktu pagi, maka ia termasuk ahli Surga.” (HR. Al-Bukhari no. 6306, 6323, Ahmad IV/122-125, an-Nasa-i VIII/279-280) dari Syaddad bin Aus Radhiyallahu ‘anhu.",

            "HR. Al-Bukhari dalam Shahiib al-Adabil Mufrad no. 701, Abu Dawud no. 5090, Ahmad V/42, hasan. Lihat Shahiih Al-Adabil Mufrad no.539",

            "HR. Al-Bukhari dalam al-Adabul Mufrad no. 1200, Abu Dawud no. 5074, An-Nasa-i VIII / 282, Ibnu Majah no. 3871, al-Hakim 1/517-518, dan lainnya dari Ibnu Umar radhiyallahu ‘anhumaa. Lihat Shahiih al-Adabul Mufrad no. 912, shahih",

            "Nabi صلي الله عليه وسلم bersabda kepada Abu Bakar ash-Shiddiq رضي الله عنه “Ucapkanlah pagi dan petang dan apabila engkau hendak tidur.” HR. Al-Bukhari dalam Al-Adabul Mufrad 1202, at-Tirmidzi no.3392 dan Abu Daud no. 5067,Lihat Shahih At- Tirmidzi no. 2798, Shahiih al-Adabil Mufrad no. 914, shahih. Lihat Silsilah al-Ahaadiits ash-Shahiihah no. 2753",

            "“Barangsiapa membacanya sebanyak tiga kali ketika pagi dan sore hari, maka tidak ada sesuatu pun yang membahayakan dirinya.” HR. At-Tirmidzi no. 3388, Abu Dawud no. 5088,Ibnu Majah no. 3869, al-Hakim 1/514, Dan Ahmad no. 446 dan 474, Tahqiq Ahmad Syakir. Dari ‘Utsman bin ‘Affan radhiyallahu ‘anhu, lihat Shahiih Ibni Majah no. 3120, al-Hakim 1/513, Shahiih al-Adabil Mufrad no. 513, Shahiih at-Targhiib wat Tarhiib 1/413 no. 655, sanad-nya shahih.",

            "“Barangsiapa membacanya sebanyak tiga kali ketika pagi dan sore, maka Allah memberikan keridhaan-Nya kepadanya pada hari Kiamat.” HR. Ahmad IV/337, Abu Dawud no. 5072, at-Tirmidzi no. 3389, Ibnu Majah no. 3870, an-Nasa-i dalam ‘Amalul Yaum wal Lailah no. 4 dan Ibnus Sunni no. 68, dishahihkan oleh Imam al-Hakim dalam al-Mustadrak 1/518 dan disetujui oleh Imam adz-Dzahabi, hasan. Lihat Shahiih At Targhiib wat Tarhiib I/415 no. 657, Shahiih At Targhiib wat Tarhiib al-Waabilish Shayyib hal. 170, Zaadul Ma’aad II/372, Silsilah al-Ahaadiits ash-Shahiihah no. 2686.",

            "HR. An-Nasa-i dalam ‘Amalul Yaum wal Lailah no. 575, dan al-Hakim 1/545, lihat Shahiih at-Targhiib wat Tarhiib 1/417 no. 661, Ash-shahiihah no. 227, hasan, dari Anas radhiyallahu ‘anhu",

            "HR. Ahmad III/406, 407, ad-Darimi II/292 dan Ibnus Sunni dalam Amalul Yaum wol Lailah no. 34, Misykaatul Mashaabiih no. 2415, Shahiihal-Jaami’ish Shaghiir no. 4674, shahih",

            "HR. Muslim no. 2693, Ahmad V/420, Silsilah al-Ahaadiits ash-Shahiihah no. 113 dan 114, Shahiih at-Targhiib wat Tarhiib 1/416 no. 660, shaahih." + "\n" + "HR. Abu Dawud no. 5077, Ibnu Majah no. 3867, dari Ab ‘Ayyasy Azzurraqy radhiyallahu ‘anhu, Shahiih Jaami’ish Shaghiir no. 6418, Misykaatul Mashaabiih no. 2395, Shahiih at-Targhiib 1/414 no. 656, shahih.\n",

            "“Barangsiapa membacanya sebanyak 100x dalam sehari, maka baginya (pahala) seperti memerdekakan sepuluh budak, ditulis seratus kebaikan, dihapus darinya seratus keburukan, mendapat perlindungan dari syaitan pada hari itu hingga sore hari. Tidaklah seseorang itu dapat mendatangkan yang lebih baik dari apa yang dibawanya kecuali ia melakukan lebih banyak lagi dari itu.” HR. Al-Bukhari no. 3293 dan 6403, Muslim IV/2071 no. 2691 (28), at-Tirmidzi no. 3468, Ibnu Majah no. 3798, dari Sahabat Abu Hurairah رضي الله عنه. Penjelasan: Dalam riwayat an-Nasa-i (‘Amalul Yaum wal Lailah no. 580) dan Ibnus Sunni no. 75 dari ‘Amr bin Syu’aib dari ayahnya dari kakeknya dengan lafadz: “Barangsiapa membaca 100x pada pagi hari dan 100x pada sore Hari.”… Jadi, dzikir ini dibaca 100x diwaktu pagi dan 100x diwaktu sore. Lihat Silsilah al-Ahaadiits ash-Shahiihah no. 2762",

            "HR. Muslim no. 2726. Syarah Muslim XVII/44. Dari Juwairiyah binti al- Harits radhiyallahu ‘anhuma",

            "HR. Ibnu Majah no. 925, Shahiih Ibni Majah 1/152 no. 753 Ibnus Sunni dalam ‘Amalul Yaum wal Lailah no. 54,110, dan Ahmad VI / 294, 305, 318, 322. Dari Ummu Salamah, shahih.",

            "HR. Muslim no. 2691 dan no. 2692, dari Abu Hurairah radhiyallahu ‘anhu Syarah Muslim XVII / 17-18, Shahiih at-Targhiib wat Tarhiib 1/413 no. 653. Jumlah yang terbanyak dari dzikir-dzikir Nabi adalah seratus diwaktu pagi dan seratus diwaktu sore. Adapun riwayat yang menyebutkan sampai seribu adalah munkar, karena haditsnya dha’if. (Silsilah al-Ahaadiits adh-Dha-’iifah no. 5296).",

            "HR. Al-Bukhari/ Fat-hul Baari XI/101 dan Muslim no.2702\n" +
                    "\n" +
                    "عَنِ ابْنِ عُمَرَ قَالَ:قَالَ رَسُو لُ اللهِ صلي الله عليه وسلم : يَااَيُّهَا النَّسُ، تُوبُواإِلَيْ اللهِ. فَإِنِّيْ اَتُوبُ فِيْ الْيَومِ إِلَيْهِ مِانَةً مَرَّةٍ\n" +
                    "\n" +
                    "Dari Ibnu ‘Umar ia berkata: “Rasulullah صلي الله عليه وسلم bersabda: ‘Wahai manusia, bertaubatlah kalian kepada Allah, sesungguhnya aku bertaubat kepada-Nya dalam sehari seratus kali.’” HR. Muslim no. 2702 (42).\n" +
                    "\n" +
                    "Dalam riwayat lain dari Agharr al-Muzani, Rasulullah صلي الله عليه وسلم bersabda:\n" +
                    "\n" +
                    "[إِنَّهُ لَيُغَانُ عَلَى قَلْبِيْ وَإِنِّيْ لأَسْتَغْفِرُ اللهَ فِي الْيَوْمِ مِائَةَ مَرَّةٍ]\n" +
                    "\n" +
                    "“Sesungguhnya hatiku terkadang lupa, dan sesungguhnya aku istighfar (minta ampun) kepada Allah dalam sehari seratus kali.” (HR. Muslim no. 2702 (41)\n" +
                    "\n" +
                    "Nabi صلي الله عليه وسلم bersabda: “Barangsiapa yang mengucapkan:\n" +
                    "\n" +
                    "أَسْتَغْفِرُ اللهَ الْعَظِيْمَ الَّذِيْ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّوْمُ وَأَتُوْبُ إِلَيْهِ\n" +
                    "\n" +
                    "‘Aku memohon ampunan kepada Allah Yang Maha Agung, Yang tidak ada Ilah yang berhak diibadahi kecuali Dia, Yang Maha hidup lagi Maha berdiri sendiri dan aku bertaubat kepada-Nya.’\n" +
                    "\n" +
                    "Maka Allah akan mengampuni dosanya meskipun ia pernah lari dari medan perang.” HR. Abu Dawud no. 1517, at-Tirmidzi no. 3577 dan al-Hakim I/511. Lihat Shahiih at-Tirmidzi III/282 no. 2381.\n" +
                    "\n" +
                    "Ayat yang menganjurkan istighfar dan taubat di antaranya: (QS. Huud: 3), (QS. An-Nuur: 31), (QS. At-Tahriim: 8) dan lain-lain."
    };

    private String[] judulDzikirPetang = {
            "Ta'awudz (Dibaca 1x)",
            "Ayat Kursi (Dibaca 1x)",
            "Surat Al-Ikhlas (Dibaca 3x)",
            "Surat Al-Falaq (Dibaca 3x)",
            "Surat An-Nas (Dibaca 3x)",
            "Dibaca 1x",
            "Dibaca 1x",
            "Sayyidul Istighfar (Dibaca 1x)",
            "Dibaca 3x",
            "Dibaca 1x",
            "Dibaca 1x",
            "Dibaca 3x",
            "Dibaca 3x",
            "Dibaca 1x",
            "Dibaca 1x",
            "Dibaca 10x atau 1x",
            "Dibaca 100x",
            "Dibaca 100x",
            "Dibaca setiap hari 100x",
            "Dibaca 3x"
    };

    private String[] isiBacaanDzikirPetang = {
            "أَعُوذُ بِاللَّهِ مِنْ الشَّيْطَانِ الرَّجِيمِ",

            "اللَّهُ لاَ إِلَهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ، لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ، لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ، مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ، يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ، وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلاَّ بِمَا شَاءَ، وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ، وَلَا يَئُودُهُ حِفْظُهُمَا، وَهُوَ الْعَلِيُّ الْعَظِيم",

            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n" +
                    "قُلْ هُوَ اللَّهُ أَحَدٌ. اللَّهُ الصَّمَدُ. لَمْ يَلِدْ وَلَمْ يُولَدْ. وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",

            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n" +
                    "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ. مِن شَرِّ مَا خَلَقَ. وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ. وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ. وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ",

            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n" +
                    "قُلْ أَعُوذُ بِرَبِّ النَّاسِ. مَلِكِ النَّاسِ. إِلَهِ النَّاسِ. مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ. الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ. مِنَ الْجِنَّةِ وَ النَّاسِ",

            "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ للهِ، وَالْحَمْدُ للهِ، لَا إِلَهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ، رَبِّ أَسْأَلُكَ خَيْرَ مَا فِي هَذِهِ اللَّيْلَةِ وَخَيْرَ مَا بَعْدَهَا، وَأَعُوذُبِكَ مِنْ شَرِّ مَا فِي هَذِهِ اللَّيْلَةِ وَشَرِّ مَا بَعْدَهَا، رَبِّ أَعُوذُبِكَ مِنَ الْكَسَلِ وَسُوءِ الْكِبَرِ، رَبِّ أَعُوذُبِكَ مِنْ عَذَابٍ فِي النَّارِ وَعَذَابٍ فِي الْقَبْرِ",

            "اللَّهُمَّ بِكَ أَمْسَيْنَا، وَبِكَ أَصْبَحْنَا،وَبِكَ نَحْيَا، وَبِكَ نَمُوتُ، وَإِلَيْكَ الْمَصِيْرُ",

            "اَللَّهُمَّ أَنْتَ رَبِّيْ لاَ إِلَـهَ إِلاَّ أَنْتَ، خَلَقْتَنِيْ وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوْذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوْءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوْءُ بِذَنْبِيْ فَاغْفِرْ لِيْ فَإِنَّهُ لاَ يَغْفِرُ الذُّنُوْبَ إِلاَّ أَنْتَ",

            "اَللَّهُمَّ عَافِنِيْ فِيْ بَدَنِيْ، اَللَّهُمَّ عَافِنِيْ فِيْ سَمْعِيْ، اَللَّهُمَّ عَافِنِيْ فِيْ بَصَرِيْ، لاَ إِلَـهَ إِلاَّ أَنْتَ. اَللَّهُمَّ إِنِّي أَعُوْذُ بِكَ مِنَ الْكُفْرِ وَالْفَقْرِ، وَأَعُوْذُ بِكَ مِنْ عَذَابِ الْقَبْرِ، لاَ إِلَـهَ إِلاَّ أَنْتَ",

            "اَللَّهُمَّ إِنِّيْ أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي الدُّنْيَا وَاْلآخِرَةِ، اَللَّهُمَّ إِنِّيْ أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي دِيْنِيْ وَدُنْيَايَ وَأَهْلِيْ وَمَالِيْ اللَّهُمَّ اسْتُرْ عَوْرَاتِى وَآمِنْ رَوْعَاتِى. اَللَّهُمَّ احْفَظْنِيْ مِنْ بَيْنِ يَدَيَّ، وَمِنْ خَلْفِيْ، وَعَنْ يَمِيْنِيْ وَعَنْ شِمَالِيْ، وَمِنْ فَوْقِيْ، وَأَعُوْذُ بِعَظَمَتِكَ أَنْ أُغْتَالَ مِنْ تَحْتِيْ",

            "اَللَّهُمَّ عَالِمَ الْغَيْبِ وَالشَّهَادَةِ فَاطِرَ السَّمَاوَاتِ وَاْلأَرْضِ، رَبَّ كُلِّ شَيْءٍ وَمَلِيْكَهُ، أَشْهَدُ أَنْ لاَ إِلَـهَ إِلاَّ أَنْتَ، أَعُوْذُ بِكَ مِنْ شَرِّ نَفْسِيْ، وَمِنْ شَرِّ الشَّيْطَانِ وَشِرْكِهِ، وَأَنْ أَقْتَرِفَ عَلَى نَفْسِيْ سُوْءًا أَوْ أَجُرُّهُ إِلَى مُسْلِمٍ",

            "بِسْمِ اللهِ الَّذِي لاَ يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي اْلأَرْضِ وَلاَ فِي السَّمَاءِ وَهُوَ السَّمِيْعُ الْعَلِيْمُ",

            "رَضِيْتُ بِاللهِ رَبًّا، وَبِاْلإِسْلاَمِ دِيْنًا، وَبِمُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ نَبِيًّا",

            "يَا حَيُّ يَا قَيُّوْمُ بِرَحْمَتِكَ أَسْتَغِيْثُ، أَصْلِحْ لِيْ شَأْنِيْ كُلَّهُ وَلاَ تَكِلْنِيْ إِلَى نَفْسِيْ طَرْفَةَ عَيْنٍ",

            "أَمْسَيْنَا عَلَى فِطْرَةِ اْلإِسْلاَمِ وَعَلَى كَلِمَةِ اْلإِخْلاَصِ، وَعَلَى دِيْنِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِيْنَا إِبْرَاهِيْمَ، حَنِيْفًا مُسْلِمًا وَمَا كَانَ مِنَ الْمُشْرِكِيْنَ",

            "لاَ إِلَـهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيْرُ",

            "لاَ إِلَـهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيْرُ",

            "سُبْحَانَ اللهِ وَبِحَمْدِهِ",

            "أَسْتَغْفِرُ اللهَ وَأَتُوْبُ إِلَيْهِ",

            "أَعُوْذُ بِكَلِمَاتِ اللهِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَ"
    };

    private String[] isiTerjemahanDzikirPetang = {
            "“Aku berlindung kepada Allah dari godaan syaitan yang terkutuk.”",

            "“Allah tidak ada Ilah (yang berhak diibadahi) melainkan Dia Yang Hidup Kekal lagi terus menerus mengurus (makhluk-Nya); tidak mengantuk dan tidak tidur. Kepunyaan-Nya apa yang ada di langit dan di bumi. Tidak ada yang dapat memberi syafa’at di sisi Allah tanpa izin-Nya. Allah mengetahui apa-apa yang (berada) dihadapan mereka, dan dibelakang mereka dan mereka tidak mengetahui apa-apa dari Ilmu Allah melainkan apa yang dikehendaki-Nya. Kursi Allah meliputi langit dan bumi. Dan Allah tidak merasa berat memelihara keduanya, Allah Mahatinggi lagi Mahabesar.” (QS. Al-Baqarah: 255)",

            "“Katakanlah, Dia-lah Allah Yang Maha Esa. Allah adalah (Rabb) yang segala sesuatu bergantung kepada-Nya. Dia tidak beranak dan tidak pula diperanakkan. Dan tidak ada seorang pun yang setara dengan-Nya.’” (QS. Al-Ikhlash: 1-4).",

            "“Katakanlah: ‘Aku berlindung kepada Rabb Yang menguasai (waktu) Shubuh dari kejahatan makhluk-Nya. Dan dari kejahatan malam apabila telah gelap gulita. Dan dari kejahatan wanita-wanita tukang sihir yang menghembus pada buhul-buhul. Serta dari kejahatan orang yang dengki apabila dia dengki.”‘ (QS. Al-Falaq: 1-5).",

            "”Katakanlah, ‘Aku berlindung kepada Rabb (yang memelihara dan menguasai) manusia. Raja manusia. Sembahan (Ilah) manusia. Dari kejahatan (bisikan) syaitan yang biasa bersembunyi. Yang membisikkan (kejahatan) ke dalam dada-dada manusia. Dari golongan jin dan manusia.’” (QS. An-Naas: 1-6).",

            "“Kami telah memasuki waktu sore dan kerajaan hanya milik Allah, segala puji hanya milik Allah. Tidak ada Ilah yang berhak diibadahi dengan benar kecuali Allah Yang Maha Esa, tiada sekutu bagi-Nya. Bagi-Nya kerajaan dan bagi-Nya pujian. Dia-lah Yang Mahakuasa atas segala sesuatu. Wahai Rabb, aku mohon kepada-Mu kebaikan di malam ini dan kebaikan sesudahnya. Aku berlindung kepada-Mu dari kejahatan malam ini dan kejahatan sesudahnya. Wahai Rabb, aku berlindung kepada-Mu dari kemalasan dan kejelekan di hari tua. Wahai Rabb, aku berlindung kepada-Mu dari siksaan di Neraka dan siksaan di kubur.”",

            "“Ya Allah, dengan rahmat dan pertolongan-Mu kami memasuki waktu sore dan dengan rahmat dan pertolongan-Mu kami memasuki waktu pagi. Dengan rahmat dan kehendak-Mu kami hidup dan dengan rahmat dan kehendak-Mu kami mati. Dan kepada-Mu tempat kembali (bagi semua makhluk).”",

            "“Ya Allah, Engkau adalah Rabb-ku, tidak ada Ilah (yang berhak diibadahi dengan benar) kecuali Engkau, Engkau-lah yang menciptakanku. Aku adalah hamba-Mu. Aku akan setia pada perjanjianku dengan-Mu semampuku. Aku berlindung kepada-Mu dari kejelekan (apa) yang kuperbuat. Aku mengakui nikmat-Mu (yang diberikan) kepadaku dan aku mengakui dosaku, oleh karena itu, ampunilah aku. Sesungguhnya tidak ada yang dapat mengampuni dosa kecuali Engkau.”",

            "“Ya Allah, selamatkanlah tubuhku (dari penyakit dan dari apa yang tidak aku inginkan). Ya Allah, selamatkanlah pendengaranku (dari penyakit dan maksiat atau dari apa yang tidak aku inginkan). Ya Allah, selamatkanlah penglihatanku, tidak ada Ilah yang berhak diibadahi dengan benar kecuali Engkau. Ya Allah, sesungguhnya aku berlindung kepada-Mu dari kekufuran dan kefakiran. Aku berlindung kepada-Mu dari siksa kubur, tidak ada Ilah yang berhak diibadahi dengan benar kecuali Engkau.”",

            "“Ya Allah, sesungguhnya aku memohon kebajikan dan keselamatan di dunia dan akhirat. Ya Allah, sesungguhnya aku memohon kebajikan dan keselamatan dalam agama, dunia, keluarga dan hartaku. Ya Allah, tutupilah auratku (aib dan sesuatu yang tidak layak dilihat orang) dan tentramkan-lah aku dari rasa takut. Ya Allah, peliharalah aku dari depan, belakang, kanan, kiri dan dari atasku. Aku berlindung dengan kebesaran-Mu, agar aku tidak disambar dari bawahku (aku berlindung dari dibenamkan ke dalam bumi).”",

            "“Ya Allah Yang Mahamengetahui yang ghaib dan yang nyata, wahai Rabb Pencipta langit dan bumi, Rabb atas segala sesuatu dan Yang Merajainya. Aku bersaksi bahwa tidak ada Ilah yang berhak diibadahi dengan benar kecuali Engkau. Aku berlindung kepada-Mu dari kejahatan diriku, syaitan dan ajakannya menyekutukan Allah (aku berlindung kepada-Mu) dari berbuat kejelekan atas diriku atau mendorong seorang muslim kepadanya.”",

            "“Dengan Menyebut Nama Allah, yang dengan Nama-Nya tidak ada satupun yang membahayakan, baik di bumi maupun dilangit. Dia-lah Yang Mahamendengar dan Maha mengetahui.”",

            "“Aku rela (ridha) Allah sebagai Rabb-ku (untukku dan orang lain), Islam sebagai agamaku dan Muhammad صلي الله عليه وسلم sebagai Nabiku (yang diutus oleh Allah).”",

            "“Wahai Rabb Yang Maha hidup, Wahai Rabb Yang Maha berdiri sendiri (tidak butuh segala sesuatu) dengan rahmat-Mu aku meminta pertolongan, perbaikilah segala urusanku dan jangan diserahkan (urusanku) kepada diriku sendiri meskipun hanya sekejap mata (tanpa mendapat pertolongan dari-Mu).”",

            "“Di waktu sore kami berada diatas fitrah agama Islam, kalimat ikhlas, agama Nabi kita Muhammad صلي الله عليه وسلم dan agama ayah kami, Ibrahim, yang berdiri di atas jalan yang lurus, muslim dan tidak tergolong orang-orang yang musyrik.”",

            "“Tidak ada Ilah yang berhak diibadahi dengan benar selain Allah Yang Maha Esa, tidak ada sekutu bagi-Nya. Bagi-Nya kerajaan dan bagi-Nya segala puji. Dan Dia Mahakuasa atas segala sesuatu.”",

            "“Tidak ada Ilah yang berhak diibadahi dengan benar selain Allah Yang Maha Esa, tidak ada sekutu bagi-Nya. Bagi-Nya kerajaan dan bagi-Nya segala puji. Dan Dia Maha kuasa atas segala sesuatu.”",

            "“Mahasuci Allah, aku memuji-Nya.”",

            "“Aku memohon ampunan kepada Allah dan bertaubat kepada-Nya.”",

            "“Aku berlindung dengan kalimat-kalimat Allah yang sempurna, dari kejahatan sesuatu yang diciptakan-Nya.”"
    };

    private String[] isiFaidahDanDalilPetang = {
            "",

            "“Barangsiapa yang membaca ayat ini ketika pagi hari, maka ia dilindungi dari (gangguan) jin hingga sore hari. Dan barangsiapa mengucapkannya ketika sore hari, maka ia dilindungi dari (gangguan) jin hingga pagi hari.” (Lihat Mustadrak Al-Hakim 1/562, Shahiih at-Targhiib wat Tarhiib 1/418 no. 662, shahih).",

            "HR. Abu Dawud no. 5082, an-Nasa-i VIII/250 dan at-Tirmidzi no. 3575, Ahmad V/312, Shahiih at-Tirmidzi no. 2829, Tuhfatul Ahwadzi no. 3646, Shahiih at-Targhiib wat Tarhiib 1/411 no. 649, hasan shahih",

            "Ibid.",

            "“Barangsiapa membaca tiga surat tersebut setiap pagi dan sore hari, maka (tiga surat tersebut) cukup baginya dari segala sesuatu”. Yakni mencegahnya dari berbagai kejahatan. ( HR. Abu Dawud no. 5082, Shahiih Abu Dawud no. 4241, Annasa-i VIII 250 dan At-Tirmizi no. 3575 , At-Tarmidzi berkata “Hadits ini hasan shahih” Ahmad V/312, dari Abdullah bin Khubaib radhiyallahu ‘anhu. Shahiih at-Tirmidzi no. 2829, Tuhfatul Ahwadzi no. 3646, Shahiih at-Targhiib wat Tarhiib 1/411 no. 649, hasan shahih).",

            "HR. Muslim no. 2723 (75), Abu Dawud no. 5071, dan at-Tirmidzi 3390, shahih dari Abdullah Ibnu Mas’ud.",

            "HR. Al-Bukhari dalam al-Adabul Mufrad no. 1199, lafazh ini adalah lafazh al-Bukhari, at-Tirmidzi no. 3391, Abu Dawud no. 5068, Ahmad 11/354, Ibnu Majah no. 3868, Dari Abu Hurairah Radhiyallahu ‘anhu. Shahiih al-Adabil Mufrad no. 911, shahih. Lihat pula Silsilah al-Ahaadiits ash-Shahiihah no. 262.",

            "“Barangsiapa membacanya dengan yakin di waktu pagi lalu ia meninggal sebelum masuk waktu sore, maka ia termasuk ahli Surga. Dan barangsiapa membacanya dengan yakin di waktu sore lalu ia meninggal sebelum masuk waktu pagi, maka ia termasuk ahli Surga.” (HR. Al-Bukhari no. 6306, 6323, Ahmad IV/122-125, an-Nasa-i VIII/279-280) dari Syaddad bin Aus Radhiyallahu ‘anhu.",

            "HR. Al-Bukhari dalam Shahiib al-Adabil Mufrad no. 701, Abu Dawud no. 5090, Ahmad V/42, hasan. Lihat Shahiih Al-Adabil Mufrad no.539",

            "HR. Al-Bukhari dalam al-Adabul Mufrad no. 1200, Abu Dawud no. 5074, An-Nasa-i VIII / 282, Ibnu Majah no. 3871, al-Hakim 1/517-518, dan lainnya dari Ibnu Umar radhiyallahu ‘anhumaa. Lihat Shahiih al-Adabul Mufrad no. 912, shahih",

            "Nabi صلي الله عليه وسلم bersabda kepada Abu Bakar ash-Shiddiq رضي الله عنه “Ucapkanlah pagi dan petang dan apabila engkau hendak tidur.” HR. Al-Bukhari dalam Al-Adabul Mufrad 1202, at-Tirmidzi no.3392 dan Abu Daud no. 5067,Lihat Shahih At- Tirmidzi no. 2798, Shahiih al-Adabil Mufrad no. 914, shahih. Lihat Silsilah al-Ahaadiits ash-Shahiihah no. 2753",

            "“Barangsiapa membacanya sebanyak tiga kali ketika pagi dan sore hari, maka tidak ada sesuatu pun yang membahayakan dirinya.” HR. At-Tirmidzi no. 3388, Abu Dawud no. 5088,Ibnu Majah no. 3869, al-Hakim 1/514, Dan Ahmad no. 446 dan 474, Tahqiq Ahmad Syakir. Dari ‘Utsman bin ‘Affan radhiyallahu ‘anhu, lihat Shahiih Ibni Majah no. 3120, al-Hakim 1/513, Shahiih al-Adabil Mufrad no. 513, Shahiih at-Targhiib wat Tarhiib 1/413 no. 655, sanad-nya shahih.",

            "“Barangsiapa membacanya sebanyak tiga kali ketika pagi dan sore, maka Allah memberikan keridhaan-Nya kepadanya pada hari Kiamat.” HR. Ahmad IV/337, Abu Dawud no. 5072, at-Tirmidzi no. 3389, Ibnu Majah no. 3870, an-Nasa-i dalam ‘Amalul Yaum wal Lailah no. 4 dan Ibnus Sunni no. 68, dishahihkan oleh Imam al-Hakim dalam al-Mustadrak 1/518 dan disetujui oleh Imam adz-Dzahabi, hasan. Lihat Shahiih At Targhiib wat Tarhiib I/415 no. 657, Shahiih At Targhiib wat Tarhiib al-Waabilish Shayyib hal. 170, Zaadul Ma’aad II/372, Silsilah al-Ahaadiits ash-Shahiihah no. 2686.",

            "HR. An-Nasa-i dalam ‘Amalul Yaum wal Lailah no. 575, dan al-Hakim 1/545, lihat Shahiih at-Targhiib wat Tarhiib 1/417 no. 661, Ash-shahiihah no. 227, hasan, dari Anas radhiyallahu ‘anhu",

            "HR. Ahmad III/406, 407, ad-Darimi II/292 dan Ibnus Sunni dalam Amalul Yaum wol Lailah no. 34, Misykaatul Mashaabiih no. 2415, Shahiihal-Jaami’ish Shaghiir no. 4674, shahih",

            "HR. Muslim no. 2693, Ahmad V/420, Silsilah al-Ahaadiits ash-Shahiihah no. 113 dan 114, Shahiih at-Targhiib wat Tarhiib 1/416 no. 660, shaahih." + "\n" + "HR. Abu Dawud no. 5077, Ibnu Majah no. 3867, dari Ab ‘Ayyasy Azzurraqy radhiyallahu ‘anhu, Shahiih Jaami’ish Shaghiir no. 6418, Misykaatul Mashaabiih no. 2395, Shahiih at-Targhiib 1/414 no. 656, shahih.\n",

            "“Barangsiapa membacanya sebanyak 100x dalam sehari, maka baginya (pahala) seperti memerdekakan sepuluh budak, ditulis seratus kebaikan, dihapus darinya seratus keburukan, mendapat perlindungan dari syaitan pada hari itu hingga sore hari. Tidaklah seseorang itu dapat mendatangkan yang lebih baik dari apa yang dibawanya kecuali ia melakukan lebih banyak lagi dari itu.” HR. Al-Bukhari no. 3293 dan 6403, Muslim IV/2071 no. 2691 (28), at-Tirmidzi no. 3468, Ibnu Majah no. 3798, dari Sahabat Abu Hurairah رضي الله عنه. Penjelasan: Dalam riwayat an-Nasa-i (‘Amalul Yaum wal Lailah no. 580) dan Ibnus Sunni no. 75 dari ‘Amr bin Syu’aib dari ayahnya dari kakeknya dengan lafadz: “Barangsiapa membaca 100x pada pagi hari dan 100x pada sore Hari.”… Jadi, dzikir ini dibaca 100x diwaktu pagi dan 100x diwaktu sore. Lihat Silsilah al-Ahaadiits ash-Shahiihah no. 2762",

            "HR. Muslim no. 2691 dan no. 2692, dari Abu Hurairah radhiyallahu ‘anhu Syarah Muslim XVII / 17-18, Shahiih at-Targhiib wat Tarhiib 1/413 no. 653. Jumlah yang terbanyak dari dzikir-dzikir Nabi adalah seratus diwaktu pagi dan seratus diwaktu sore. Adapun riwayat yang menyebutkan sampai seribu adalah munkar, karena haditsnya dha’if. (Silsilah al-Ahaadiits adh-Dha-’iifah no. 5296).",

            "HR. Al-Bukhari/ Fat-hul Baari XI/101 dan Muslim no.2702\n" +
                    "\n" +
                    "عَنِ ابْنِ عُمَرَ قَالَ:قَالَ رَسُو لُ اللهِ صلي الله عليه وسلم : يَااَيُّهَا النَّسُ، تُوبُواإِلَيْ اللهِ. فَإِنِّيْ اَتُوبُ فِيْ الْيَومِ إِلَيْهِ مِانَةً مَرَّةٍ\n" +
                    "\n" +
                    "Dari Ibnu ‘Umar ia berkata: “Rasulullah صلي الله عليه وسلم bersabda: ‘Wahai manusia, bertaubatlah kalian kepada Allah, sesungguhnya aku bertaubat kepada-Nya dalam sehari seratus kali.’” HR. Muslim no. 2702 (42).\n" +
                    "\n" +
                    "Dalam riwayat lain dari Agharr al-Muzani, Rasulullah صلي الله عليه وسلم bersabda:\n" +
                    "\n" +
                    "[إِنَّهُ لَيُغَانُ عَلَى قَلْبِيْ وَإِنِّيْ لأَسْتَغْفِرُ اللهَ فِي الْيَوْمِ مِائَةَ مَرَّةٍ]\n" +
                    "\n" +
                    "“Sesungguhnya hatiku terkadang lupa, dan sesungguhnya aku istighfar (minta ampun) kepada Allah dalam sehari seratus kali.” (HR. Muslim no. 2702 (41)\n" +
                    "\n" +
                    "Nabi صلي الله عليه وسلم bersabda: “Barangsiapa yang mengucapkan:\n" +
                    "\n" +
                    "أَسْتَغْفِرُ اللهَ الْعَظِيْمَ الَّذِيْ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّوْمُ وَأَتُوْبُ إِلَيْهِ\n" +
                    "\n" +
                    "‘Aku memohon ampunan kepada Allah Yang Maha Agung, Yang tidak ada Ilah yang berhak diibadahi kecuali Dia, Yang Maha hidup lagi Maha berdiri sendiri dan aku bertaubat kepada-Nya.’\n" +
                    "\n" +
                    "Maka Allah akan mengampuni dosanya meskipun ia pernah lari dari medan perang.” HR. Abu Dawud no. 1517, at-Tirmidzi no. 3577 dan al-Hakim I/511. Lihat Shahiih at-Tirmidzi III/282 no. 2381.\n" +
                    "\n" +
                    "Ayat yang menganjurkan istighfar dan taubat di antaranya: (QS. Huud: 3), (QS. An-Nuur: 31), (QS. At-Tahriim: 8) dan lain-lain.",

            "HR. Ahmad 11/290, an-Nasa-i dalam ‘Amalul Yaum wal Lailah no. 596, Shahiih at-Targhiib wat Tarhiib 1/412 no. 652, Shahiih al-Jaami ‘ish Shaghiir no. 6427"
    };

    private int waktu = 1; //1. Pagi//2. Petang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_pagi_dan_petang);

        waktu = getIntent().getIntExtra("waktu", 1);

        setLayout();
        getData();
    }

    void setLayout() {
        this.vPager = (ViewPager) findViewById(R.id.vPager);
    }

    void getData() {
        FragmentIsiDzikirActivity ff = new FragmentIsiDzikirActivity();
        fragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        if (waktu == 1) {
            for (int i = 0; i < judulDzikir.length; i++) {
                ((MyPagerAdapter) fragmentPagerAdapter).addFragment(ff, judulDzikir[i], isiBacaanDzikir[i], "\n" + isiTerjemahanDzikir[i], isiFaidahDanDalil[i]);
            }
        } else {
            for (int i = 0; i < judulDzikirPetang.length; i++) {
                ((MyPagerAdapter) fragmentPagerAdapter).addFragment(ff, judulDzikirPetang[i], isiBacaanDzikirPetang[i], "\n" + isiTerjemahanDzikirPetang[i], isiFaidahDanDalilPetang[i]);
            }
        }
        vPager.setAdapter(fragmentPagerAdapter);
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<FragmentIsiDzikirActivity> listFragment = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fragmentManager, ArrayList<FragmentIsiDzikirActivity> fragments) {
            super(fragmentManager);
            listFragment = fragments;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return listFragment.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        public void addFragment(FragmentIsiDzikirActivity fragment, String judulNa, String isiNa, String terjemahanNa, String faidahDanDalil) {
            listFragment.add(fragment.newInstance(judulNa, isiNa, terjemahanNa, faidahDanDalil));
        }
    }
}
