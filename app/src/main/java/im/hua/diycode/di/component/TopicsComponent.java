package im.hua.diycode.di.component;

import dagger.Component;
import im.hua.diycode.di.PerActivity;

/**
 * Created by hua on 2016/11/17.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class})
public interface TopicsComponent {
}
