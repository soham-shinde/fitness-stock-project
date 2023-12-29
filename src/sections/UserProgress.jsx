import React, { useEffect, useState } from 'react'
import { getUserTaskListById } from '../api-client/api-module';

export default function UserProgress({user}) {
    const [calories, setCalories] = useState(0);
    const [duration, setDuration] = useState(0);
    const [select, setSelect] = useState();
    const [loader, setLoader] = useState(false);

   

    useEffect(() => {
        fetchData();
    }, []);



    const fetchData = async () => {
        try {
            const list = await getUserTaskListById(user.id);
            list.forEach(element => {
                setCalories(calories+Number.parseInt(element.calories))
                if(element.iscompleted){
                    setDuration(duration+Number.parseInt(element.duration))
                }
            });
            setLoader(true);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
    return (
        <>
            {loader&&<><h2>Customer Progress</h2>
            <div className="progress-cards">
                <div className="progress-card">
                    <h3>Current Weight</h3>
                    <p className="measurement">{user.weight} Kg</p>
                </div>

                <div className="progress-card">
                    <h3>Current Height</h3>
                    <p className="measurement">{user.height} In</p>
                </div>

                <div className="progress-card">
                    <h3>Workout Duration</h3>
                    <p className="measurement">{duration} hours</p>
                </div>

                <div className="progress-card">
                    <h3>Total Calories Burnt</h3>
                    <p className="measurement">{calories} kcal</p>
                </div>

            </div></>}

        </>
    )
}
