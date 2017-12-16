# admin page for database to access or update data
from django.contrib import admin
#from .models import user, cattle, userdata, user_register_request, cattle_reg_appliction_req, cattle_fa
from .models import user_register_request, cattle_reg_appliction_req

#admin.site.register(user)
#admin.site.register(cattle)
#admin.site.register(userdata)
admin.site.register(cattle_reg_appliction_req)
admin.site.register(user_register_request)
#admin.site.register(cattle_fa)

