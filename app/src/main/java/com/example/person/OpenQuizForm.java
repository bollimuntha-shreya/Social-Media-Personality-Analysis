package com.example.person;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpenQuizForm extends AppCompatActivity {
    Button submit;
    RadioGroup one, two, three, four, five, six, seven, eight, nine, ten;
    RadioButton onea, oneb, onec, oned, onee;
    RadioButton twoa, twob, twoc, twod, twoe;
    RadioButton threea, threeb, threec, threed, threee;
    RadioButton foura, fourb, fourc, fourd, foure;
    RadioButton fivea, fiveb, fivec, fived, fivee;
    RadioButton sixa, sixb, sixc, sixd, sixe;
    RadioButton sevena, sevenb, sevenc, sevend, sevene;
    RadioButton eighta, eightb, eightc, eightd, eighte;
    RadioButton ninea, nineb, ninec, nined, ninee;
    RadioButton tena, tenb, tenc, tend, tene;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_form);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs()) {
                    int values[] = new int[10];
                    values[0] = getSelectedRadioButtonValue(one);
                    values[1] = getSelectedRadioButtonValue(two);
                    values[2] = getSelectedRadioButtonValue(three);
                    values[3] = getSelectedRadioButtonValue(four);
                    values[4] = getSelectedRadioButtonValue(five);
                    values[5] = getSelectedRadioButtonValue(six);
                    values[6] = getSelectedRadioButtonValue(seven);
                    values[7] = getSelectedRadioButtonValue(eight);
                    values[8] = getSelectedRadioButtonValue(nine);
                    values[9] = getSelectedRadioButtonValue(ten);

                    int salesQuestionsCount = 4;
                    int totalSalesValue = values[0] + values[2] + values[4] + values[6];
                    float averageSalesValue = (float) totalSalesValue / salesQuestionsCount;
                    float salesFitPercentage = (averageSalesValue / 5) * 100;
                    Log.d("Sales Percent", String.valueOf(salesFitPercentage));

                    int techQuestionsCount = 4;
                    int totalTechValue = values[1] + values[3] + values[7] + values[9];
                    float averageTechValue = (float) totalTechValue / techQuestionsCount;
                    float techFitPercentage = (averageTechValue / 5) * 100;

                    Intent intent = new Intent(OpenQuizForm.this, Result.class);
                    intent.putExtra("salesPercent", salesFitPercentage);
                    intent.putExtra("techPercent", techFitPercentage);
                    startActivity(intent);
                }
            }
        });

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        ten = findViewById(R.id.ten);

        onea = findViewById(R.id.onea);
        oneb = findViewById(R.id.oneb);
        onec = findViewById(R.id.onec);
        oned = findViewById(R.id.oned);
        onee = findViewById(R.id.onee);

        twoa = findViewById(R.id.twoa);
        twob = findViewById(R.id.twob);
        twoc = findViewById(R.id.twoc);
        twod = findViewById(R.id.twod);
        twoe = findViewById(R.id.twoe);

        threea = findViewById(R.id.threea);
        threeb = findViewById(R.id.threeb);
        threec = findViewById(R.id.threec);
        threed = findViewById(R.id.threed);
        threee = findViewById(R.id.threee);

        foura = findViewById(R.id.foura);
        fourb = findViewById(R.id.fourb);
        fourc = findViewById(R.id.fourc);
        fourd = findViewById(R.id.fourd);
        foure = findViewById(R.id.foure);

        fivea = findViewById(R.id.fivea);
        fiveb = findViewById(R.id.fiveb);
        fivec = findViewById(R.id.fivec);
        fived = findViewById(R.id.fived);
        fivee = findViewById(R.id.fivee);

        sixa = findViewById(R.id.sixa);
        sixb = findViewById(R.id.sixb);
        sixc = findViewById(R.id.sixc);
        sixd = findViewById(R.id.sixd);
        sixe = findViewById(R.id.sixe);

        sevena = findViewById(R.id.sevena);
        sevenb = findViewById(R.id.sevenb);
        sevenc = findViewById(R.id.sevenc);
        sevend = findViewById(R.id.sevend);
        sevene = findViewById(R.id.sevene);

        eighta = findViewById(R.id.eighta);
        eightb = findViewById(R.id.eightb);
        eightc = findViewById(R.id.eightc);
        eightd = findViewById(R.id.eightd);
        eighte = findViewById(R.id.eighte);

        ninea = findViewById(R.id.ninea);
        nineb = findViewById(R.id.nineb);
        ninec = findViewById(R.id.ninec);
        nined = findViewById(R.id.nined);
        ninee = findViewById(R.id.ninee);

        tena = findViewById(R.id.tena);
        tenb = findViewById(R.id.tenb);
        tenc = findViewById(R.id.tenc);
        tend = findViewById(R.id.tend);
        tene = findViewById(R.id.tene);
    }

    protected Boolean checkInputs() {
        int ans1 = one.getCheckedRadioButtonId();
        int ans2 = two.getCheckedRadioButtonId();
        int ans3 = three.getCheckedRadioButtonId();
        int ans4 = four.getCheckedRadioButtonId();
        int ans5 = five.getCheckedRadioButtonId();
        int ans6 = six.getCheckedRadioButtonId();
        int ans7 = seven.getCheckedRadioButtonId();
        int ans8 = eight.getCheckedRadioButtonId();
        int ans9 = nine.getCheckedRadioButtonId();
        int ans10 = ten.getCheckedRadioButtonId();

        if(ans1 == -1 || ans2 == -1 || ans3 == -1 || ans4 == -1 || ans5 == -1 || ans6 == -1 || ans7 == -1 || ans8 == -1 || ans9 == -1 || ans10 == -1) {
            Toast.makeText(OpenQuizForm.this, "Answer all the questions", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private int getSelectedRadioButtonValue(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);

            if (selectedRadioButton.getId() == R.id.onea || selectedRadioButton.getId() == R.id.twoa ||
                    selectedRadioButton.getId() == R.id.threea || selectedRadioButton.getId() == R.id.foura ||
                    selectedRadioButton.getId() == R.id.fivea || selectedRadioButton.getId() == R.id.sixa ||
                    selectedRadioButton.getId() == R.id.sevena || selectedRadioButton.getId() == R.id.eighta ||
                    selectedRadioButton.getId() == R.id.ninea || selectedRadioButton.getId() == R.id.tena) {
                return 1;
            } else if (selectedRadioButton.getId() == R.id.oneb || selectedRadioButton.getId() == R.id.twob ||
                    selectedRadioButton.getId() == R.id.threeb || selectedRadioButton.getId() == R.id.fourb ||
                    selectedRadioButton.getId() == R.id.fiveb || selectedRadioButton.getId() == R.id.sixb ||
                    selectedRadioButton.getId() == R.id.sevenb || selectedRadioButton.getId() == R.id.eightb ||
                    selectedRadioButton.getId() == R.id.nineb || selectedRadioButton.getId() == R.id.tenb) {
                return 2;
            } else if (selectedRadioButton.getId() == R.id.onec || selectedRadioButton.getId() == R.id.twoc ||
                    selectedRadioButton.getId() == R.id.threec || selectedRadioButton.getId() == R.id.fourc ||
                    selectedRadioButton.getId() == R.id.fivec || selectedRadioButton.getId() == R.id.sixc ||
                    selectedRadioButton.getId() == R.id.sevenc || selectedRadioButton.getId() == R.id.eightc ||
                    selectedRadioButton.getId() == R.id.ninec || selectedRadioButton.getId() == R.id.tenc) {
                return 3;
            } else if (selectedRadioButton.getId() == R.id.oned || selectedRadioButton.getId() == R.id.twod ||
                    selectedRadioButton.getId() == R.id.threed || selectedRadioButton.getId() == R.id.fourd ||
                    selectedRadioButton.getId() == R.id.fivee || selectedRadioButton.getId() == R.id.sixd ||
                    selectedRadioButton.getId() == R.id.sevend || selectedRadioButton.getId() == R.id.eightd ||
                    selectedRadioButton.getId() == R.id.nined || selectedRadioButton.getId() == R.id.tend) {
                return 4;
            } else if (selectedRadioButton.getId() == R.id.onee || selectedRadioButton.getId() == R.id.twoe ||
                    selectedRadioButton.getId() == R.id.threee || selectedRadioButton.getId() == R.id.fivee ||
                    selectedRadioButton.getId() == R.id.sixe || selectedRadioButton.getId() == R.id.sevene ||
                    selectedRadioButton.getId() == R.id.eighte || selectedRadioButton.getId() == R.id.ninee ||
                    selectedRadioButton.getId() == R.id.tene) {
                return 5;
            }
        }
        return 0;
    }

}
