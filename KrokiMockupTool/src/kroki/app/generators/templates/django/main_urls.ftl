from django.conf.urls import patterns, include, url
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', '${project_ccname}.views.home', name='home'),
    # url(r'^${project_ccname}/', include('${project_ccname}.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    url(r'^${project_ccname}/', include('st_forms.urls', namespace="st_forms")),
    url(r'^admin/', include(admin.site.urls)),
)