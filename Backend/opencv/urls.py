"""opencv URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""

# This file in use for providing url to our apis to use in android
from django.conf.urls import url
from django.contrib import admin
from . import views
from rest_framework.urlpatterns import format_suffix_patterns

from . import views

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^test/', views.test),
    url(r'^name/', views.match_nose.as_view()),
    url(r'^login/', views.login.as_view()),
    url(r'^register/', views.request_register.as_view()),
    url(r'^reg_cattle/', views.register_animal.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)
