package com.slabiak.tomek.howlongapp.ui.main;

import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.ui.addreport.AddReportContract;
import com.slabiak.tomek.howlongapp.ui.addreport.AddReportPresenter;
import com.slabiak.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;
import com.slabiak.tomek.howlongapp.util.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by tslabiak on 19.03.2018.
 */

public class AddReportPresenterTest {
    @Mock AddReportContract.View mockView;
    @Mock AppDataManager mockAppDataManaer;

    private AddReportPresenter mPresenter;
    private BaseSchedulerProvider mBaseSchedulerProvider;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mBaseSchedulerProvider = new ImmediateSchedulerProvider();
        mPresenter = new AddReportPresenter(mockAppDataManaer, mBaseSchedulerProvider);
        mPresenter.attachView(mockView);

    }

/*    @Test
    public void shouldFindRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        list.add(restaurant);
        ApiResponse reponse = new ApiResponse();
        reponse.setRestaurants(list);
        doReturn(reponse).when(mockAppDataManaer).getLocalResponse();
        Restaurant restaurant2 = mPresenter.findReastaurant(1);
        Assert.assertEquals(restaurant, restaurant2);
    }*/

/*    @Test
    public void shouldAddReportWhenButtonClicked(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        String author = "author";
        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).addReport(1,15, author);
        mPresenter.addReport(1,15, author);
        verify(mockView).startMainActivity();

    }*/
}
