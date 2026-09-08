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