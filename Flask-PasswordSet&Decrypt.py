from flask import render_template
from flask import Flask
from flask import request, session, url_for, redirect, flash
from flask import request
from datetime import date
from datetime import datetime
app = Flask(__name__)
users=[]
app.secret_key = 'project7'

password_file = open('password_list.txt', 'r')
password_list = [line.strip('\n').split(',') for line in password_file.readlines()]


class User:
    def __init__(self, username, password):
        self.username = username
        self.password = password
    def __repr__(self):
        return f'<User: {self.username}>'

#home page
@app.route('/')
def index():
    if users == []:
        return redirect(url_for('login'))
    try:
        user = [x for x in users if x.username == session['user']][0]
    except:
        flash('Not Logged In')
        return redirect(url_for('login'))
    if user not in users:
        flash('Not Logged In')
        return redirect(url_for('login'))
    return render_template('base.html', title='Home')  
@app.route('/logout/') 
#loging out of session
def logout():
    session['user'] = ''
    return redirect(url_for('login'))
@app.route('/login/', methods=['GET','POST']) 
#logging in 
def login():
    if request.method == "POST":
        username = request.form['username']
        password = request.form['password']
        try:
            user = [x for x in users if x.username == username][0]
            if user and user.password == password:
                session['user'] = user.username
                return redirect(url_for('index'))
        except:
            ip = request.environ.get('HTTP_X_REAL_IP', request.remote_addr)  
            today = datetime.now()
            export_list=['Attempt',ip,today,'\n']
            with open('failed_attempts.txt', 'a') as f:
                for item in export_list:
                    f.write("%s\n" % item)
            flash('Not able to find account.Please Register')
            return render_template('login.html', title='Home')
    else:
        return render_template('login.html', title='Home')
@app.route('/register/', methods=['GET','POST'])
#registering
def register():
    if request.method == "POST":
        username = request.form['username']
        password = request.form['password']
        if (len(password) == 12 and any(x.isupper() for x in password) and any(x.islower() for x in password) and any(x.isdigit() for x in password) and any(not x.isalnum() for x in password)):
            user=User(username = username, password = password)
            users.append(user)
            session['user'] = user.username
            flash('Your account was created')
            return redirect(url_for('index'))
        else:
            password_error="Password does not meet requirements."
            return render_template('register.html', title = 'Home',password_error = password_error)
    else:
        return render_template('register.html', title = 'Home')
def password_check(password):
    if (len(password) == 12 and any(x.isupper() for x in password) and any(x.islower() for x in password) and any(x.isdigit() for x in password) and any(not x.isalnum() for x in password)):
        for line in password_list:
            if line[0] in password:
                password_check = 1
                print('here')
            else:
                password_check = 2
            return password_check
    else:
        password_check = 0
        return password_check
@app.route('/update/', methods=['GET','POST'])
def update_password():
    password_error = ''
    if request.method == "POST":
        password = request.form['password']
        password_test = password_check(password)
        if password_test == 2:
            print('here')
            user = [x for x in users if x.username == session['user']][0]
            user.password = password
            flash('Your password was updated.')
            return redirect(url_for('index'))
        else:
            if password_test == 0:
                password_error = 'Password does not meet requirements.'
            elif password_test == 1:
                password_error = 'Password is too common.'
            return render_template('password_update.html', title = 'Home',password_error = password_error)
    else:
        try:
            user = [x for x in users if x.username == session['user']][0]
        except:
            flash('Not Logged In')
            return redirect(url_for('login'))
        return render_template('password_update.html', title = 'Home')
if __name__ == "__main__":
    app.run()

