# -*- coding: utf-8 -*-
# Generated by Django 1.11.2 on 2017-07-19 14:43
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('opencv', '0023_auto_20170719_2010'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='cattle',
            name='fid',
        ),
    ]