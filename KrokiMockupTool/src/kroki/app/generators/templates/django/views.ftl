from django.shortcuts import render
from django.contrib.auth import authenticate,login as auth_login
from django.http import HttpResponseRedirect

def login(request):
    if request.method == 'GET':
        return render(request,'st_forms/login.html')
    elif request.method == 'POST':
    	username=request.POST['login']
    	password=request.POST['password']
    	user = authenticate(username=username, password=password)
    	if user is not None:
    	    if user.is_active:
    	        auth_login(request, user)
    	        return HttpResponseRedirect('main')
    	    else:
    	        return render(request,'st_forms/login.html')
    	else:
    	    return render(request,'st_forms/login.html')
    	    
def main(request):
    return render(request,'st_forms/main.html')