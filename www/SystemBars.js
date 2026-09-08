var exec = require('cordova/exec');

/**
 * StatusBar + NavigationBar 숨김
 */
exports.hide = function (success, error) {

    exec(
        success,
        error,
        'SystemBars',
        'hide',
        []
    );
};


/**
 * StatusBar + NavigationBar 다시 표시
 */
exports.show = function (success, error) {

    exec(
        success,
        error,
        'SystemBars',
        'show',
        []
    );
};


/**
 * 다크모드 시 상태바 텍스트 색상 변경
 */
exports.setDarkMode = function (darkMode, success, error) {
     console.log('[SystemBars.js] setDarkMode called:', darkMode);
    exec(
        success,
        error,
        'SystemBars',
        'setDarkMode',
        [darkMode]
    );
};