const

    // Dev mode
    devBuild = (process.env.NODE_ENV !== 'production'),

    // Modules

    // General
    gulp =                  require('gulp'),
    noop =                  require('gulp-noop'),
    rename =                require('gulp-rename'),
    del =                   require('del'),
    newer =                 require('gulp-newer'),

    // Styles
    sass =                  require('gulp-sass'),
    autoprefixer =          require('gulp-autoprefixer'),
    cssnano =               require('gulp-cssnano'),

    // Images
    imagemin =              require('gulp-imagemin'),
    pngquant =              require('imagemin-pngquant'),
    cache =                 require('gulp-cache'),
    svgmin =                require('gulp-svgmin'),
    ico =                   require('gulp-to-ico')

// Scripts
    concat =                require('gulp-concat'),
    deporder =              require('gulp-deporder'),
    terser =                require('gulp-terser'),
    sourcemaps = devBuild ? require('gulp-sourcemaps') : null,
    browserSync =           require('browser-sync'),

    // Paths
    src = '.frontend/src',
    out = '.frontend/build',

    paths = {
        raw: {
            styles: `${src}/styles/style.sass`,
            scripts: [`${src}/scripts/**/*.js`, `!${src}/scripts/library/**/*`],
            images: [`${src}/images/**/*.+(jpg|jpeg|png|tiff|webp)`, `!${src}/images/raw/**`, `!${src}/images/favicon.png`],
            svg: `${src}/images/sprites.svg`,
            ico: `${src}/images/favicon.png`,
            media: `${src}/media/**/*`,
        },
        dist: {
            styles: `${out}/styles`,
            scripts: `${out}/scripts`,
            images: `${out}/images`,
            svg: `${out}/images`,
            ico: `${out}/images`,
            media: `${out}/media`,
        },
        watch: {
            styles: `${src}/styles/**/*.sass`,
            scripts: [`${src}/scripts/**/*.js`, `!${src}/scripts/library/**`],
            images: [`${src}/images/**/*.+(jpg|jpeg|png|tiff|webp)`, `!${src}/images/raw/**/*`, `!${src}/images/favicon.png`],
            views: 'views/**/*.pug',
            svg: `${src}/images/sprites.svg`,
            ico: `${src}/images/favicon.png`,
            media: `${src}/media/**/*`,
        }
    }
;

gulp.task('styles', async function () {
    return gulp.src(paths.raw.styles)
        .pipe(sass({
            outputStyle: 'nested',
            imagePath: '/images/',
            sourcemaps: true,
            precision: 3,
            errLogToConsole: true
        })).on('error', sass.logError)
        .pipe(autoprefixer(['last 15 versions', '> 1%', 'ie 8', 'ie 7'], { cascade: true }))
        .pipe(cssnano())
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest(paths.dist.styles))
        .pipe(browserSync.reload({stream: true}))
});

gulp.task('scripts', async function () {
    return gulp.src(paths.raw.scripts)
        .pipe(sourcemaps ? sourcemaps.init() : null)
        .pipe(deporder ? deporder() : noop()).on('error', (er) => log(er))
        .pipe(concat('script.js'))
        .pipe(terser())
        .pipe(sourcemaps ? sourcemaps.write(): noop())
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest(paths.dist.scripts))
        .pipe(browserSync.reload({stream: true}))
});

gulp.task('views', async function () {
    return gulp.src(paths.watch.views)
        .pipe(browserSync.reload({stream: true}))
});

gulp.task('images', async function () {
    return gulp.src(paths.raw.images)
        .pipe(newer(paths.dist.images))
        .pipe(cache(imagemin({
            optimisationLevel: 5,
            interlaced: true,
            progressive: true,
            use: [pngquant()]
        })))
        .pipe(gulp.dest(paths.dist.images))
        .pipe(browserSync.reload({stream: true}))
});

gulp.task('svg', async function () {
    return gulp.src(paths.raw.svg)
        .pipe(svgmin({
            plugins: [
                {cleanupIDs : false},
                {removeUselessDefs: false},
                {convertStyleToAttrs: false},
            ]
        }))
        .pipe(gulp.dest(paths.dist.svg));
});

gulp.task('ico', async function () {
    return gulp.src(paths.raw.ico)
        .pipe(ico('favicon.ico'))
        .pipe(gulp.dest(paths.dist.ico));
});

gulp.task('media', async function () {
    return gulp.src(paths.raw.media)
        .pipe(gulp.dest(paths.dist.media));
});

gulp.task('clean', async function () {
    del.sync(out);
    if (devBuild) cache.clearAll();
})

gulp.task('build', gulp.parallel('images', 'views', 'styles', 'scripts', 'svg', 'ico', 'media'));

gulp.task('browser-sync', async function () {
    browserSync({
        proxy: 'http://127.0.0.1:5000/',
        notify: false,
        // browser: 'C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe'
    });
});

gulp.task('watch', async function () {
    gulp.watch(paths.watch.styles, gulp.parallel('styles'));
    gulp.watch(paths.watch.scripts, gulp.parallel('scripts'));
    gulp.watch(paths.watch.views, gulp.parallel('views'));
    gulp.watch(paths.watch.images, gulp.parallel('images'));
    gulp.watch(paths.watch.svg, gulp.parallel('svg'));
    gulp.watch(paths.watch.ico, gulp.parallel('ico'));
    gulp.watch(paths.watch.media, gulp.parallel('media'));
});

gulp.task('default', gulp.parallel('clean', 'build', 'browser-sync', 'watch'));