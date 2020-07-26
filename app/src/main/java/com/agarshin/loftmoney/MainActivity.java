package com.agarshin.loftmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.os.Handler;
import com.agarshin.loftmoney.cells.money.MoneyAdapter;
import com.agarshin.loftmoney.cells.money.MoneyAdapterClick;
import com.agarshin.loftmoney.cells.money.MoneyCellModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MoneyAdapter moneyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.costRecyclerView);
        moneyAdapter = new MoneyAdapter();
        moneyAdapter.setMoneyAdapterClick(new MoneyAdapterClick() {
            @Override
            public void onMonetClick(MoneyCellModel moneyCellModel) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(moneyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false));

        moneyAdapter.setData(generateExpenses());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                moneyAdapter.addData(generateIncomes());
            }
        },3000);
    }

    private List<MoneyCellModel> generateExpenses() {
        List<MoneyCellModel> moneyCellModels = new ArrayList<>();
        moneyCellModels.add(new MoneyCellModel("Молоко", "70 ₽", R.color.expenseColor));
        moneyCellModels.add(new MoneyCellModel("Зубная щётка", "70 ₽", R.color.expenseColor));
        moneyCellModels.add(new MoneyCellModel("Сковородка с антипригарным покрытием", "1670 ₽", R.color.expenseColor));

        return moneyCellModels;
    }

    private List<MoneyCellModel> generateIncomes() {
        List<MoneyCellModel> moneyCellModels = new ArrayList<>();
        moneyCellModels.add(new MoneyCellModel("Зарплата", "70000 ₽", R.color.incomeColor));
        moneyCellModels.add(new MoneyCellModel("Премия", "7000 ₽", R.color.incomeColor));
        moneyCellModels.add(new MoneyCellModel("Олег наконец-то вернул долг", "300 000 ₽", R.color.incomeColor));

        return moneyCellModels;
    }

}
