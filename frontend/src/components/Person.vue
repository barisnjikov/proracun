<template>
  <div class="person">
    <h1>Persons</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="person.firstName" placeholder="first name">
    <input type="text" v-model="person.lastName" placeholder="last name">

    <button @click="createNewPerson()">Create Person</button>

    <div v-if="showResponse"><h6>Person created with Id: {{ response }}</h6></div>

    <button v-if="showResponse" @click="retrievePerson()">Retrieve preson {{person.id}} data from database</button>

    <h4 v-if="showRetrievedPerson">Retrieved Person {{retrievedPerson.firstName}} {{retrievedPerson.lastName}}</h4>

  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'person',

    data () {
      return {
        response: [],
        errors: [],
        person: {
          lastName: '',
          firstName: '',
          id: 0
        },
        showResponse: false,
        retrievedPerson: {},
        showRetrievedPerson: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createNewPerson () {

        api.createPerson(this.person.firstName, this.person.lastName).then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data;
            this.person.id = response.data;
            console.log('Created new Person with Id ' + response.data);
            this.showResponse = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      retrievePerson () {
        api.getPerson(this.person.id).then(response => {
            // JSON responses are automatically parsed.
            this.retrievedPerson = response.data;
            this.showRetrievedPerson = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    }
  }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
