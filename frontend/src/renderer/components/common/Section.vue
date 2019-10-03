<template>


        <div id="modal-ter" class="modal">
            <div class="modal-background"></div>
            <div class="modal-card">
              <header class="modal-card-head">
                <p class="modal-card-title">Modal title</p>
                <button class="delete close-modal" data-target="modal-ter"></button>
              </header>
              <section class="modal-card-body">
                <div class="content">a\na\n</div>
              </section>
            </div>
        </div>

        <!--<div class="container" id="index-type">
            <div class="bd-snippet-preview ">
              &lt;!&ndash;<router-link class="button is-danger is-focused" :to="{name: 'pubPoem'}" @click="showPubPoem()">>News List</router-link&ndash;&gt;
              <a class="button is-danger is-focused" @click="showPubPoem()">PubPoem</a>
              <a class="button is-warning is-focused" @click="sortBy('createTime')">UserPoem</a>
              <a class="button is-info is-focused" @click="sortBy('starCount')">ConcernPoem</a>
            </div>
            <hr>
        </div>-->
    </div>
</template>


<script>
import pubPoem from "../../container/Square/pubPoem";
import userPoem from "../../container/Square/userPoem";
import concernPoem from "../../container/Square/concernPoem";

export default {
  name: "Section",
  components: { pubPoem, userPoem, concernPoem},
  props: { tagList: Array },
  data() {
    return {
        whichPage: 1
    };
  },
  mounted() {
    let self = this;
    $("nav.tabs")
      .find("li")
      .click(function() {
        if (!$(this).hasClass("is-active")) {
          $("nav.tabs")
            .find("li")
            .each(function(k, v) {
              $(v).removeClass("is-active");
            });
          $(this).addClass("is-active");
          self.$emit("tagEvent", $(this).attr("type"));
          //todo:应该减少dom移出操作
          $("#index-main").hide();
          newBee("#index-type").animation(500, function() {
            $("#index-main").show();
          });
        }
      });
  },
  destroyed() {},
  methods: {
    showPubPoem () {

    }
  }
};
</script>
