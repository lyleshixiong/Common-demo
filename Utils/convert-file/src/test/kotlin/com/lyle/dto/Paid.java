package com.lyle.dto;

import java.util.HashMap;
import java.util.Map;

public class Paid {
    public static final Map<String, String> paids = new HashMap<String, String>();
    public static final Map<String, String> paidout = new HashMap<String, String>();

    static {
        // MyCard paid
        // Simon add start
        paids.put("mycard-ingame-HK", "MyCard-點數卡(香港)");
        paids.put("mycard-ingame-TW", "MyCard-點數卡(臺灣)");

        paids.put("mycard-ingame-1", "MyCard-點數卡(虛擬)");
        paids.put("mycard-ingame-2", "MyCard-點數卡(一般通路)");
        paids.put("mycard-ingame-3", "MyCard-點數卡(會員)");
        paids.put("mycard-ingame-5", "MyCard-點數卡(海外港澳舊價)");
        paids.put("mycard-ingame-7", "MyCard-點數卡(海外大馬)");
        paids.put("mycard-ingame-8", "MyCard-點數卡(海外港澳新價)");
        paids.put("mycard-ingame-18", "MyCard-點數卡(全家超商通路)");
        paids.put("mycard-ingame-19", "MyCard-點數卡(全家之外超商通路)");
        paids.put("mycard-ingame-20", "MyCard-點數卡(行動遊戲卡)");
        paids.put("mycard-ingame-31", "MyCard-點數卡(贈品卡)");
        paids.put("mycard-ingame-32", "MyCard-點數卡(海外大馬新價ver15.09)");
        paids.put("mycard-ingame-34", "MyCard-點數卡(海外大馬新價ver17.03)");
        paids.put("mycard-ingame-36", "MyCard-點數卡(海外新價ver17.06)");
        paids.put("mycard-ingame-38", "MyCard-點數卡(海外大馬新價ver17.10)");
        paids.put("mycard-ingame-42", "MyCard-點數卡(海外大馬新價ver18.03)");

        paids.put("gash-COPGAM05-MYR", "Gash-馬幣點數卡");
        paids.put("gash-COPGAM03", "Gash-香港點數卡");

        paids.put("bank-hk", "大額匯款(香港)");
        paids.put("bank-tw", "大額匯款(臺灣)");

        paids.put("hkgame_home", "到府收款(香港)");

        // Simon add end

        paids.put("mycard-ingame", "MyCard-點數卡");
        paids.put("mycard-billing-GTSHWEBATM", "MyCard-webATM-國泰世華");
        paids.put("mycard-billing-TELFET01", "MyCard-遠傳電信");
        paids.put("mycard-point", "MyCard-電子錢包");
        paids.put("mycard-billing-TELCHT03", "MyCard-中華電信");
        paids.put("mycard-billing-TELSON04", "MyCard-亞太電信");
        paids.put("mycard-billing-BNK82201", "MyCard-台灣地區信用卡付款(3D驗證)");
        paids.put("mycard-billing-TELCHT01", "MyCard-中華電信市內電話");
        paids.put("mycard-billing-BNK80801", "MyCard-webATM-玉山銀行");
        paids.put("mycard-billing-TELVIBO", "MyCard-台灣之星(威寶)");
        paids.put("mycard-billing-TELTCC01", "MyCard-台灣大哥大");
        paids.put("mycard-billing-ZGYZWEBATM", "MyCard-webATM-中國郵政");
        paids.put("mycard-billing-TBFBWEBATM", "MyCard-webATM-台北富邦");
        paids.put("mycard-billing-TXWEBATM", "MyCard-webATM-台新銀行");
        paids.put("mycard-billing-ZGXTWEBATM", "MyCard-webATM-中國信託");
        paids.put("mycard-billing-TWWEBATM", "MyCard-webATM-台灣銀行");
        paids.put("mycard-billing-ZHWEBATM", "MyCard-webATM-彰化銀行");
        paids.put("mycard-billing-HNWEBATM", "MyCard-webATM-華南銀行");
        paids.put("mycard-billing-SHWEBATM", "MyCard-webATM-上海銀行");
        paids.put("mycard-billing-HZJKWEBATM", "MyCard-webATM-合作金庫");
        paids.put("mycard-billing-DYWEBATM", "MyCard-webATM-第一銀行");
        paids.put("mycard-billing-XGWEBATM", "MyCard-webATM-新光銀行");
        paids.put("mycard-billing-ZFWEBATM", "MyCard-webATM-兆豐銀行");
        paids.put("mycard-billing-TDWEBATM", "MyCard-webATM-土地銀行");
        paids.put("mycard-billing-YINLIAN", "MyCard-銀聯在線支付");
        paids.put("mycard-billing-CAIFUTONG", "MyCard-財付通");
        paids.put("mycard-billing-KUAIQIAN", "MyCard-快錢");
        paids.put("mycard-billing-FUYOU", "MyCard-富友支付");
        paids.put("mycard-billing-ALIPAY", "MyCard-支付寶");
        paids.put("mycard-billing-PAYPAL", "MyCard-PayPal");
        paids.put("mycard-billing-RSWEBATM", "MyCard-webATM-日盛銀行");
        paids.put("mycard-billing-BANKWEBATM", "MyCard-webATM-銀行轉帳");

        // Gash paid
        // paids.put("gash-COPGAM05", "Gash-點數卡");
        paids.put("gash-BNK82201", "Gash-信用卡");
        paids.put("gash-TELTCC01", "Gash-台灣大哥大");
        paids.put("gash-BNK80804", "Gash-支付寶");
        paids.put("gash-TELCHT06", "Gash-中華電信HiNet");
        paids.put("gash-COPPAL01", "Gash-PayPal");
        paids.put("gash-TELFET01", "Gash-遠傳電信");
        paids.put("gash-BNK80801", "Gash-webATM-玉山銀行");
        paids.put("gash-TELCHT07", "Gash-中華電信市內電話");
        paids.put("gash-BNKRBS01", "Gash-國際信用卡");
        paids.put("gash-TELVIBO", "Gash- 台灣之星(威寶)");
        paids.put("gash-TELCHT05", "Gash-中華電信手機");
        paids.put("gash-TELSON04", "Gash-亞太電信");

        paids.put("gash-COPGAM05-TWD", "Gash-台灣點數卡");
        paids.put("gash-COPGAM05-HKD", "Gash-香港點數卡");
        paids.put("gash-COPGAM09", "Gash-電子錢包");

        // partner
        paids.put("partner", "partner");

        // googleplay
        paids.put("GooglePlay", "GooglePlay");

        // ios
        paids.put("Appstore", "Appstore");
        //MOL
        paids.put("mol-ewallet", "MOL電子錢包");
        paids.put("mol-points", "MOL點卡");
        paids.put("mol-mobile", "MOL小額付費");
        // bank
        paids.put("bank", "大額匯款");
        paids.put("twgame_home", "到府收款");
        paids.put("ChinaTrust-Credit", "中國信託(臺灣信用卡)");
        paids.put("ChinaTrust-UnionPay", "中國信託(銀聯卡)");
        paids.put("happypay", "支付樂(信用卡)");
        paidout.put("COPGAM05", "Gash-點數卡");
        paidout.put("BNK82201", "Gash-信用卡");
        paidout.put("TELTCC01", "Gash-台灣大哥大");
        paidout.put("BNK80804", "Gash-支付寶");
        paidout.put("TELCHT06", "Gash-中華電信HiNet");
        paidout.put("COPPAL01", "Gash-PayPal");
        paidout.put("TELFET01", "Gash-遠傳電信");
        paidout.put("BNK80801", "Gash-webATM-玉山銀行");
        paidout.put("TELCHT07", "Gash-中華電信市內電話");
        paidout.put("BNKRBS01", "Gash-國際信用卡");
        paidout.put("TELVIBO", "Gash- 台灣之星(威寶)");
        paidout.put("TELCHT05", "Gash-中華電信手機");
        paidout.put("TELSON04", "Gash-亞太電信");
        paidout.put("COPGAM03", "Gash-香港點數卡");
        paidout.put("COPGAM05-TWD", "Gash-台灣點數卡");
        paidout.put("COPGAM05-HKD", "Gash-香港點數卡");
        paidout.put("COPGAM09", "Gash-電子錢包");

        paidout.putAll(paids);

    }

    public static Map<String, String> getPaids() {
        return paids;
    }

    public static String getPaidStr(String paid) {
        return paids.get(paid);
    }

    public static String getPayStr(String paid) {
        return paidout.get(paid);
    }

}
