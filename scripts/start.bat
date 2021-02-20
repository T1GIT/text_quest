SET /P NAME=<appname.txt
heroku ps:scale web=1 --app %NAME%