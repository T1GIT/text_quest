SET /P NAME=<appname.txt
heroku ps:scale web=0 --app %NAME%