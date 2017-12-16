# Model file is used to create a table in database
from django.db import models


'''class user(models.Model):
    uid = models.CharField(max_length=20, primary_key=True)
    fname = models.CharField(max_length=20)
    lname = models.CharField(max_length=20)
    dob = models.CharField(max_length=20)
    mobile = models.CharField(max_length=20)
    address = models.CharField(max_length=50)
    cattle_owned = models.IntegerField
    total_no_claims = models.IntegerField()
    total_no_false_claim = models.IntegerField()
    total_no_approved_claims = models.IntegerField()

    def __str__(self):
        return self.fname + " " + self.uid


class cattle_face(models.Model):
    uid = models.ForeignKey(user, on_delete=models.CASCADE)
    fid = models.CharField(max_length=20)

    def __str__(self):
        return self.uid


class cattle_fa(models.Model):
    uid = models.ForeignKey(user, on_delete=models.CASCADE)
    fid = models.CharField(max_length=20)

    def __str__(self):
        return str(self.fid)


class cattle(models.Model):
    uid = models.ForeignKey(user, on_delete=models.CASCADE)
    fid = models.ForeignKey(cattle_fa, on_delete=models.CASCADE)
    cid = models.CharField(max_length=20)
    breed = models.CharField(max_length=20)
    color = models.CharField(max_length=10)
    horn_size = models.CharField(max_length=10)
    cattle_status = models.CharField(max_length=10)

    def __str__(self):
        return self.cid


class userdata(models.Model):
    uid = models.OneToOneField(user, on_delete=models.CASCADE)
    uname = models.CharField(max_length=20)
    password = models.CharField(max_length=20)

    def __str__(self):
        return self.uname
'''

class user_register_request(models.Model):
    fname = models.CharField(max_length=20)
    mname = models.CharField(max_length=20)
    lname = models.CharField(max_length=20)
    uid = models.CharField(max_length=50)
    aadhar = models.CharField(max_length=14)
    password = models.CharField(max_length=225)
    mobile = models.CharField(max_length=10)
    address = models.CharField(max_length=100)

    def __str__(self):
        return self.fname


class cattle_reg_appliction_req(models.Model):
    uid = models.ForeignKey(user_register_request, on_delete=models.CASCADE)
    cid = models.CharField(max_length=20)
    breed = models.CharField(max_length=20)
    color = models.CharField(max_length=20)
    horn_size = models.CharField(max_length=20)
    cattle_status = models.CharField(max_length=10)

    def __str__(self):
        return str(self.uid)