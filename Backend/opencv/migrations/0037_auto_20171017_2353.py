# -*- coding: utf-8 -*-
# Generated by Django 1.11.5 on 2017-10-17 18:23
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('opencv', '0036_auto_20171017_2352'),
    ]

    operations = [
        migrations.AlterField(
            model_name='cattle_reg_appliction_req',
            name='uid',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='opencv.user'),
        ),
    ]