package cordova.plugins.systembars;

import android.app.Activity;
import android.view.View;
import android.view.Window;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;


public class SystemBars extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("hide".equals(action)) {
            hideSystemBars(callbackContext);
            return true;
        }

        if ("show".equals(action)) {
            showSystemBars(callbackContext);
            return true;
        }

        if ("setDarkMode".equals(action)) {
            boolean darkMode = args.optBoolean(0, false);
            setDarkMode(darkMode, callbackContext);
            return true;
        }

        return false;
    }


    /**
     * StatusBar + NavigationBar 숨김
     */
    private void hideSystemBars(CallbackContext callbackContext) {
        Activity activity = cordova.getActivity();
        activity.runOnUiThread(() -> {

            try {

                Window window = activity.getWindow();

                WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());

                if (controller == null) {
                    callbackContext.error("WindowInsetsController is null");
                    return;
                }

                /*
                 * Status Bar + Navigation Bar 숨김
                 */
                controller.hide(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars());

                /*
                 * 사용자가 화면 가장자리에서 스와이프하면 System Bar를 일시적으로 표시
                 */
                controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

                callbackContext.success();
                
            } catch (Exception e) {
                callbackContext.error("Failed to hide system bars: " + e.getMessage());
            }
        });
    }


    /**
     * StatusBar + NavigationBar 다시 표시
     */
    private void showSystemBars(CallbackContext callbackContext) {

        Activity activity = cordova.getActivity();

        activity.runOnUiThread(() -> {
            try {

                Window window = activity.getWindow();

                WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());

                if (controller == null) {
                    callbackContext.error("WindowInsetsController is null");

                    return;
                }
                
                /*
                 * Status Bar + Navigation Bar 표시
                 */
                controller.show(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars());

                callbackContext.success();

            } catch (Exception e) {
                callbackContext.error("Failed to show system bars: " + e.getMessage());
            }
        });
    }

    /**
     * 다크모드에 따라 StatusBar / NavigationBar 아이콘 색상 변경
     *
     * darkMode = true
     *   -> 밝은 아이콘
     *
     * darkMode = false
     *   -> 어두운 아이콘
     */
    private void setDarkMode(boolean darkMode, CallbackContext callbackContext) {

        Activity activity = cordova.getActivity();

        activity.runOnUiThread(() -> {

            try {

                Window window = activity.getWindow();

                WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());

                /*
                 * true  -> 검은색 아이콘
                 * false -> 흰색 아이콘
                 */
                controller.setAppearanceLightStatusBars(!darkMode);

                callbackContext.success();

            } catch (Exception e) {

                callbackContext.error(
                        "Failed to change system bar appearance: "
                                + e.getMessage()
                );
            }
        });
    }
}