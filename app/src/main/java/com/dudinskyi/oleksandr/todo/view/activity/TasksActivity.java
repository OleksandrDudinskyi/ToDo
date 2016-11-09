package com.dudinskyi.oleksandr.todo.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.presenter.TaskActivityPresenter;
import com.dudinskyi.oleksandr.todo.view.TasksView;
import com.dudinskyi.oleksandr.todo.view.adapter.PagerAdapter;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TasksActivity extends AppCompatActivity implements TasksView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TaskActivityPresenter taskPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.pending));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.done));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(tabSelectedListener);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> taskPresenter.updateTasks());

        initPresenter();
    }

    private void initPresenter() {
        taskPresenter = new TaskActivityPresenter();
        taskPresenter.addView(this);
        taskPresenter.initialize();
        taskPresenter.updateTasks();
    }

    @Override
    protected void onDestroy() {
        tabLayout.removeOnTabSelectedListener(tabSelectedListener);
        taskPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onTaskUpdated() {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, R.string.task_updated_txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskUpdatedError() {
        Snackbar.make(swipeRefreshLayout, R.string.error_try_again_txt, Snackbar.LENGTH_LONG).
                setAction(getString(R.string.retry), v -> taskPresenter.updateTasks()).show();
    }

    @Override
    public void noInternetConnection() {
        Snackbar.make(swipeRefreshLayout, R.string.message_no_internet, Snackbar.LENGTH_LONG).
                setAction(getString(R.string.retry), v -> taskPresenter.updateTasks()).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
