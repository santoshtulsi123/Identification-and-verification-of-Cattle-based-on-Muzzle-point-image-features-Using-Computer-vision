from rest_framework import serializers
from .models import user, cattle, userdata


class userserializer(serializers.ModelSerializer):
    class Meta:
        model = user
        fields = '__all__'
        # fields = ('fname','lname')


class cattleserializer(serializers.ModelSerializer):
    class Meta:
        model = cattle
        fields = '__all__'

class loginserializer(serializers.ModelSerializer):
    class Meta:
        model = userdata
        fields = ('uid',)
