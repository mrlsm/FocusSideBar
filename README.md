# FocusSideBar

可聚焦字母快速定位侧边栏 类似于通讯录字符侧边栏

## 截图
![Screenshot](/screenshot.gif)

## 将 FocusSideBar 包含在您的项目中
With gradle:

```groovy
dependencies {
   implementation 'com.github.mrlsm:FocusSideBar:1.0'
}
```

## 在布局文件中使用 FocusSideBar
```xml
    <com.mrlsm.focussidebar.FocusSideBar
        android:id="@+id/focus_side_bar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingTop="20dp"
        android:paddingRight="6dp"
        android:paddingBottom="20dp"
        app:sidebar_lazy_respond="false"
        app:sidebar_position="right"
        app:sidebar_show_center_tips="true"
        app:sidebar_text_alignment="center"
        app:sidebar_text_size="12sp" />
```

## 属性描述
|              属性              |            格式            |   默认项   |            描述             |
| :----------------------------: | :------------------------: | :--------: | :-------------------------: |
|       sidebar_text_color       |           color            | Color.GRAY |       侧边栏文字颜色        |
|     sidebar_focus_bg_color     |           color            |  #179FED   |   侧边栏焦点文字背景颜色    |
|  sidebar_center_tips_bg_color  |           color            | #30000000  |     侧边栏提示背景颜色      |
| sidebar_center_tips_text_color |           color            | Color.GRAY |     侧边栏提示文字颜色      |
|       sidebar_text_size        |         dimension          |    12sp    |       侧边栏文字大小        |
|    sidebar_center_tips_size    |         dimension          |    28dp    |    侧边栏中间 tips 大小     |
|        sidebar_position        |    enum  {right,  left}    |   right    |         侧边栏位置          |
|     sidebar_text_alignment     | enum {center, left, right} |   center   |          对齐方式           |
|      sidebar_lazy_respond      |          boolean           |   false    | true：ACTION_UP 后 列表跳转 |
|    sidebar_show_center_tips    |          boolean           |   false    |     true: 显示中间tips      |



也可以在java代码中设置这些属性：

```java
FocusSideBar sideBar = (FocusSideBar) findViewById(R.id.focus_side_bar);
sideBar.setTextColor(Color.BLACK);
sideBar.setPosition(FocusSideBar.POSITION_LEFT);
sideBar.setTextAlign(FocusSideBar.TEXT_ALIGN_CENTER);
sideBar.setLazyRespond(true);

sideBar.setCurrentIndex(pos); // 设置当前侧边栏字符位置
```

## 监听器设置
```java
FocusSideBar sideBar = (WaveSideBar) findViewById(R.id.focus_side_bar);
sideBar.setOnSelectIndexItemListener(new FocusSideBar.OnSelectIndexItemListener() {
    @Override
    public void onSelectIndexItem(String index) {
        // Do something here ....
    }
});
```

## 帮助类 SpellingUtils

提供中文提取首个汉字大写字母，并提供 String 列表的 汉字 字符 数字 排序功能

### 参考：

[WaveSideBar](https://github.com/gjiazhe/WaveSideBar)
