from django.conf.urls import patterns, url

from st_forms import views

urlpatterns = patterns('',
    url(r'^$', views.login, name='login'),
    url(r'^login/$', views.login, name='login'),
    url(r'^main/$', views.main, name='main'),
)