<template>

    <v-card outlined>
        <v-card-title>
            PatientManagment # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="PatientId" v-model="item.patientId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="DiagnosisId" v-model="item.diagnosisId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="ExamId" v-model="item.examId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="ReceptionDt" v-model="item.receptionDt" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Status" v-model="item.status" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'PatientManagmentViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/patientManagments/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

