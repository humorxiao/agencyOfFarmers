<template>
<div>
  <adminnavar></adminnavar>
  <div class="row clearfix div-bg-color">
    <div class="col-md-10 col-md-offset-1 column">
      <div class="tabbable" id="tabs-952416">
        <br>
        <adminuserandnewstabs></adminuserandnewstabs>
        <div class="tab-content div-bg-color">
          <one_adminuserandnewsfather></one_adminuserandnewsfather>
          <two_adminuserandnewsfather></two_adminuserandnewsfather>
          <three_adminuserandnewsfather></three_adminuserandnewsfather>
          <four_adminuserandnews></four_adminuserandnews>
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
import myone_adminUserAndNewsFather from './admin/one_adminUserAndNewsFather.vue'
import mythree_adminUserAndNewsFather from './admin/three_adminUserAndNewsFather.vue'
import myfour_adminUserAndNews from './commonComponents/four_adminUserAndNews.vue'
import myadminfooter from './commonComponents/adminfooter.vue'
import mytwo_adminUserAndNewsFather from './admin/two_adminUserAndNewsFather'
export default {
  name: "userAndnewsManagePage",
  components: {
    adminnavar: myadminNavar,
    adminuserandnewstabs: myadminUserAndNewsTabs,
    one_adminuserandnewsfather: myone_adminUserAndNewsFather,
    three_adminuserandnewsfather: mythree_adminUserAndNewsFather,
    four_adminuserandnews: myfour_adminUserAndNews,
    adminfooter: myadminfooter,
    two_adminuserandnewsfather: mytwo_adminUserAndNewsFather
  }
}
</script>

<style scoped>
</style>
