package com.mrlsm.focussidebar.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.mrlsm.focussidebar.FocusSideBar;
import com.mrlsm.focussidebar.SpellingUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> focusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initViews() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final FocusSideBar focusSideBar = findViewById(R.id.focus_side_bar);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new FocusSideBarAdapter(focusList, R.layout.item_focus_side_bar));

        final List<String> enNameList = new ArrayList<>();
        for (String value : focusList) {
            String key = SpellingUtils.getFirstLetter(value.substring(0, 1));
            key = key.startsWith("#") ? "#" : key;
            if (!enNameList.contains(key)) {
                enNameList.add(key);
            }
        }
        focusSideBar.setIndexItems(enNameList.toArray(new String[enNameList.size()]));
        focusSideBar.setOnSelectIndexItemListener(new FocusSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < focusList.size(); i++) {
                    String key = SpellingUtils.getFirstLetter(focusList.get(i).substring(0, 1));
                    if (("#".equals(index) && key.startsWith("#")) || TextUtils.equals(key, index)) {
                        layoutManager.scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                final int position = layoutManager.findFirstCompletelyVisibleItemPosition();
                String key = SpellingUtils.getFirstLetter(focusList.get(position).substring(0, 1));
                focusSideBar.setCurrentIndex(enNameList.indexOf(key));
            }
        });
    }

    private void initData() {
        focusList = new ArrayList<>();
        focusList.add("北京");
        focusList.add("天津");
        focusList.add("河北");
        focusList.add("山西");
        focusList.add("内蒙");
        focusList.add("辽宁");
        focusList.add("吉林");
        focusList.add("黑龙江");
        focusList.add("上海");
        focusList.add("江苏");
        focusList.add("浙江");
        focusList.add("安徽");
        focusList.add("福建");
        focusList.add("江西");
        focusList.add("山东");
        focusList.add("河南");
        focusList.add("湖北");
        focusList.add("湖南");
        focusList.add("广东");
        focusList.add("广西");
        focusList.add("海南");
        focusList.add("四川");
        focusList.add("贵州");
        focusList.add("云南");
        focusList.add("西藏");
        focusList.add("陕西");
        focusList.add("甘肃");
        focusList.add("青海");
        focusList.add("宁夏");
        focusList.add("新疆");
        focusList.add("台湾");
        focusList.add("香港");
        focusList.add("澳门");
        focusList.add("123");
        focusList.add("*873");
        focusList.add("$&&^");
        focusList = SpellingUtils.stringSort(focusList);
    }
}
