
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReceptionReceptionManager from "./components/listers/ReceptionReceptionCards"
import ReceptionReceptionDetail from "./components/listers/ReceptionReceptionDetail"

import DiagnosisDiagnosisManager from "./components/listers/DiagnosisDiagnosisCards"
import DiagnosisDiagnosisDetail from "./components/listers/DiagnosisDiagnosisDetail"

import ExaminationExaminationManager from "./components/listers/ExaminationExaminationCards"
import ExaminationExaminationDetail from "./components/listers/ExaminationExaminationDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/receptions/receptions',
                name: 'ReceptionReceptionManager',
                component: ReceptionReceptionManager
            },
            {
                path: '/receptions/receptions/:id',
                name: 'ReceptionReceptionDetail',
                component: ReceptionReceptionDetail
            },

            {
                path: '/diagnoses/diagnoses',
                name: 'DiagnosisDiagnosisManager',
                component: DiagnosisDiagnosisManager
            },
            {
                path: '/diagnoses/diagnoses/:id',
                name: 'DiagnosisDiagnosisDetail',
                component: DiagnosisDiagnosisDetail
            },

            {
                path: '/examinations/examinations',
                name: 'ExaminationExaminationManager',
                component: ExaminationExaminationManager
            },
            {
                path: '/examinations/examinations/:id',
                name: 'ExaminationExaminationDetail',
                component: ExaminationExaminationDetail
            },




    ]
})
