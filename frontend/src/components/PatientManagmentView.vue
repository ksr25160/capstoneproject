<template>

    <v-data-table
        :headers="headers"
        :items="patientManagment"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'PatientManagmentView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "patientId", value: "patientId" },
                { text: "diagnosisId", value: "diagnosisId" },
                { text: "examId", value: "examId" },
                { text: "receptionDt", value: "receptionDt" },
                { text: "status", value: "status" },
            ],
            patientManagment : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/patientManagments'))

            temp.data._embedded.patientManagments.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.patientManagment = temp.data._embedded.patientManagments;
        },
        methods: {
        }
    }
</script>

