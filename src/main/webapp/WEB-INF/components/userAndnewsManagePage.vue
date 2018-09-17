<template>
<div>
  <adminnavar></adminnavar>
  <div class="row clearfix div-bg-color">
    <div class="col-md-10 col-md-offset-1 column">
      <div class="tabbable" id="tabs-952416">
        <br>
        <adminuserandnewstabs></adminuserandnewstabs>
        <div class="tab-content div-bg-color">
          <f_adminuserandnews></f_adminuserandnews>
          <forth_adminuserandnews></forth_adminuserandnews>
          <s_adminuserandnews></s_adminuserandnews>
          <t_adminuserandnews></t_adminuserandnews>
        </div>
      </div>
    </div>
  </div>
  <adminfooter></adminfooter>
</div>
</template>

<script>
import myadminNavar from './commonComponents/adminUserAndNewsNavar.vue'
import myadminUserAndNewsTabs from  './commonComponents/adminUserAndNewsTabs.vue'
import myf_adminUserAndNews from './commonComponents/f_adminUserAndNews.vue'
import mys_adminUserAndNews from './commonComponents/s_adminUserAndNews.vue'
import myt_adminUserAndNews from './commonComponents/t_adminUserAndNews.vue'
import myadminfooter from './commonComponents/adminfooter.vue'
import myforth_adminUserAndNews from './commonComponents/forth_adminUserAndNews.vue'
export default {
        name: "userAndnewsManagePage",
      components: {
        adminnavar: myadminNavar,
        adminuserandnewstabs: myadminUserAndNewsTabs,
        f_adminuserandnews : myf_adminUserAndNews,
        s_adminuserandnews: mys_adminUserAndNews,
        t_adminuserandnews: myt_adminUserAndNews,
        adminfooter : myadminfooter,
        forth_adminuserandnews: myforth_adminUserAndNews
      }
    }
</script>

<style scoped>

</style>
