import React, { useEffect, useState } from 'react'
import { getMembershipPlanById, getPlanspurchasePlanById } from '../api-client/api-module';

export default function UserMember({ pid }) {

    const [membership, setMembership] = useState();
    const [plan, setPlan] = useState();
    const [loader, setLoader] = useState(false);

    useEffect(() => {
        fetchData();
    }, []);



    const fetchData = async () => {
        try {
            const raw = await getPlanspurchasePlanById(pid);
            setPlan(raw);
            const raw1 = await getMembershipPlanById(raw.enrolledplan);
            setMembership(raw1);
            console.log(raw1, raw);
            setLoader(true)

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
    return (
        <> {loader && <>
            <h2>Membership Management</h2>
            <div className="membership-cards overflow-scroll">
                <div className="membership-card">
                    <div className="membership-icon">
                        <img src={`/img/${membership.type}.png`} alt="Membership Icon" />
                    </div>
                    <div className="membership-details">
                        <h3 className="membership-type">{membership.name}</h3>
                        <p className="membership-duration">{plan.expirydate.split('T')[0]}</p>
                        <ul className="features" dangerouslySetInnerHTML={{ __html: `<li>${membership.features.replace(/\n/g, '</li><li>')}</li>` }}>

                        </ul>
                        <p className="renewal-option">Renewal: Auto</p>
                    </div>
                </div>

            </div>
        </>}</>
    )
}
