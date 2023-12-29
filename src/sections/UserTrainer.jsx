import React, { Profiler, useEffect, useState } from 'react'
import { getMembershipPlanById, getPlanspurchasePlanById, getTrainersById, getTrainersList } from '../api-client/api-module';

export default function UserTrainer({pid}) {

    const [trainer, setTrainer] = useState();
    const [loader, setLoader] = useState(false);

    useEffect(() => {
        fetchData();
    }, []);



    const fetchData = async () => {
        try {
            const raw = await getPlanspurchasePlanById(pid);
            const raw1 = await getMembershipPlanById(raw.enrolledplan);
            const raw3 = await getTrainersById(raw1.planstrainer);
            setTrainer(raw3);
            console.log(raw3);
            setLoader(true)

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };


    return (
        <>
            <h2>Trainers Management</h2>
            <div className="trainers-cards">
                <div className="trainer-card">
                    <div className="trainer-info">
                        <img src={loader&&trainer.image} alt='' />
                        <h3 className="trainer-name">{loader&&trainer.name}</h3>
                        <p className="trainer-specialization">Specialization: {loader&&trainer.specialization}</p>
                        <p className="trainer-specialization">Experience: {loader&&trainer.experience} year</p>
                        <p className="contact-info-1">Email: {loader&&trainer.username}<br />Phone: {loader&&trainer.contactno}</p>
                    </div>
                </div>
            </div>
        </>
    )
}
