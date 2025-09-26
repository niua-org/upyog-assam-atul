import React, { useEffect, useState } from "react";
import { FormStep, CardLabel, Dropdown, TextInput } from "@upyog/digit-ui-react-components";

const AreaMapping = ({ t, config, onSelect, formData, searchResult }) => {

  // State for dropdown options
  const [districts, setDistricts] = useState([]);
  const [planningAreas, setPlanningAreas] = useState([]);
  const [ppAuthorities, setPpAuthorities] = useState([]);
  const [bpAuthorities, setBpAuthorities] = useState([]);
  const [revenueVillages, setRevenueVillages] = useState([]);
  const [mouzas, setMouzas] = useState([]);
  // State for all dropdown values
  const [district, setDistrict] = useState(formData?.areaMapping?.district ||  "");
  const [planningArea, setPlanningArea] = useState(null);
  const [ppAuthority, setPpAuthority] = useState(null);
  const [bpAuthority, setBpAuthority] = useState(null);
  const [revenueVillage, setRevenueVillage] = useState(null);
  const [mouza, setMouza] = useState(mouzas.find(opt=>opt.code ===searchResult?.areaMapping?.mouza)|| formData?.areaMapping?.mouza || "");
  const [ward, setWard] = useState(searchResult?.areaMapping?.ward || formData?.areaMapping?.ward || "");  

  // Fetch data from MDMS
  const { data: areaMappingData, isLoading } = Digit.Hooks.useEnabledMDMS(
    "as", 
    "BPA", 
    [
      { name: "districts" }, 
      { name: "planningAreas" }, 
      { name: "ppAuthorities" }, 
      { name: "bpAuthorities" }, 
      { name: "revenueVillages" }, 
      { name: "mouzas" }
    ],
    {
      select: (data) => {
        const formattedData = data?.BPA || {};
        return formattedData;
      },
    }
  );

  // Initialize districts from MDMS data
  useEffect(() => {
    if (areaMappingData?.districts) {
      const formattedDistricts = areaMappingData.districts.map((district) => ({
        code: district.districtCode,
        name: district.districtName,
        i18nKey: district.districtCode,
      }));
      setDistricts(formattedDistricts);
      if (searchResult?.areaMapping?.district) {
        const selected = formattedDistricts.find(d => d.i18nKey === searchResult.areaMapping.district);
        if (selected) setDistrict(selected);
      }
    }
  }, [areaMappingData, searchResult]);

  // Update planning areas when district changes
  useEffect(() => {
    if (district && areaMappingData?.planningAreas) {
      // Filter planning areas based on selected district
      const filteredPlanningAreas = areaMappingData.planningAreas
      .filter(area => area.districtCode === district?.code)
      .map(area => ({
        code: area.planningAreaCode,
        name: area.planningAreaName,
        i18nKey: area.planningAreaCode,
      }));
      setPlanningAreas(filteredPlanningAreas);

      if (searchResult?.areaMapping?.planningArea) {
        const selected = filteredPlanningAreas.find(p => p.i18nKey === searchResult.areaMapping.planningArea);
        if (selected) setPlanningArea(selected);
      } else setPlanningArea(null);
    }
  }, [district, areaMappingData, searchResult]);

  // Update PP authorities when planning area changes
  useEffect(() => {
    if (planningArea && areaMappingData?.ppAuthorities) {
      // Filter PP authorities based on selected planning area
        const filteredPpAuthorities = areaMappingData.ppAuthorities
        .filter(authority => authority.planningAreaCode === planningArea?.code)
        .map(authority => ({
          code: authority.ppAuthorityCode,
          name: authority.ppAuthorityName,
          i18nKey: authority.ppAuthorityCode,
        }));

      setPpAuthorities(filteredPpAuthorities);

      if (searchResult?.areaMapping?.planningPermitAuthority) {
        const selected = filteredPpAuthorities.find(p => p.i18nKey === searchResult.areaMapping.planningPermitAuthority);
        if (selected) setPpAuthority(selected);
      } else setPpAuthority(null);
    }
  }, [planningArea, areaMappingData, searchResult]);

  // Update BP authorities when PP authority changes
  useEffect(() => {
    if (ppAuthority && areaMappingData?.bpAuthorities) {
      // Filter BP authorities based on selected PP authority
      const filteredBpAuthorities = areaMappingData.bpAuthorities
      .filter(authority => authority.ppAuthorityCode === ppAuthority?.code)
      .map(authority => ({
        code: authority.bpAuthorityCode,
        name: authority.bpAuthorityName,
        i18nKey: authority.bpAuthorityCode,
      }));
      setBpAuthorities(filteredBpAuthorities);

      if (searchResult?.areaMapping?.buildingPermitAuthority) {
        const selected = filteredBpAuthorities.find(b => b.i18nKey === searchResult.areaMapping.buildingPermitAuthority);
        if (selected) setBpAuthority(selected);
      } else setBpAuthority(null);
    }
  }, [ppAuthority, areaMappingData, searchResult]);

  // Update revenue villages when BP authority changes
  useEffect(() => {
    if (bpAuthority && areaMappingData?.revenueVillages) {
      const filteredRevenueVillages = areaMappingData.revenueVillages
      .filter(village => village.bpAuthorityCode === bpAuthority?.code)
      .map(village => ({
        code: village.revenueVillageCode,
        name: village.revenueVillageName,
        i18nKey: village.revenueVillageCode,
      }));
      setRevenueVillages(filteredRevenueVillages);

      if (searchResult?.areaMapping?.revenueVillage) {
        const selected = filteredRevenueVillages.find(r => r.i18nKey === searchResult.areaMapping.revenueVillage);
        if (selected) setRevenueVillage(selected);
      } else setRevenueVillage(null);
    }
  }, [bpAuthority, areaMappingData, searchResult]);

  // Update mouzas when revenue village changes
  useEffect(() => {
    if (revenueVillage && areaMappingData?.mouzas) {
      // Filter mouzas based on selected revenue village
      const filteredMouzas = areaMappingData.mouzas
      .filter(mouza => mouza.revenueVillageCode === revenueVillage?.code)
      .map(mouza => ({
        code: mouza.mouzaCode,
        name: mouza.mouzaName,
        i18nKey: mouza.mouzaCode,
      }));
      setMouzas(filteredMouzas);
      if (searchResult?.areaMapping?.mouza) {
        const selected = filteredMouzas.find(m => m.i18nKey === searchResult.areaMapping.mouza);
        if (selected) setMouza(selected);
      } else setMouza(null);
    }
  }, [revenueVillage, areaMappingData, searchResult]);

  // Go next
  const goNext = () => {
    let areaMappingStep = {
      district,
      planningArea,
      ppAuthority,
      bpAuthority,
      revenueVillage,
      mouza,
      ward
    };

    onSelect(config.key, { ...formData[config.key], ...areaMappingStep });
  };

  const onSkip = () => onSelect();

  return (
    <React.Fragment>
      <FormStep
        config={config}
        onSelect={goNext}
        onSkip={onSkip}
        t={t}
        isDisabled={
          !district ||
          !planningArea ||
          !ppAuthority ||
          !bpAuthority ||
          !revenueVillage ||
          !mouza ||
          !ward
        }
      >
        <div>
          {/* District */}
          <CardLabel>{`${t("DISTRICT")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={districts}
            optionKey="i18nKey"
            id="district"
            selected={district}
            select={setDistrict}
            placeholder={isLoading ? t("LOADING_DISTRICTS") : t("SELECT_DISTRICT")}
          />

          {/* Planning Area */}
          <CardLabel>{`${t("PLANNING_AREA")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={planningAreas}
            optionKey="i18nKey" 
            selected={planningArea}
            select={setPlanningArea} 
            placeholder={!district ? t("SELECT_DISTRICT_FIRST") : t("SELECT_PLANNING_AREA")} 
          />

          {/* PP Authority */}
          <CardLabel>{`${t("PP_AUTHORITY")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={ppAuthorities}
            optionKey="i18nKey"
            selected={ppAuthority}
            select={setPpAuthority} 
            placeholder={!planningArea ? t("SELECT_PLANNING_AREA_FIRST") : t("SELECT_PP_AUTHORITY")}
          />

          {/* BP Authority */}
          <CardLabel>{`${t("BP_AUTHORITY")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={bpAuthorities}
            optionKey="i18nKey"
            selected={bpAuthority}
            select={setBpAuthority}
            placeholder={!ppAuthority ? t("SELECT_PP_AUTHORITY_FIRST") : t("SELECT_BP_AUTHORITY")}
          />

          {/* Revenue Village */}
          <CardLabel>{`${t("REVENUE_VILLAGE")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={revenueVillages}
            optionKey="i18nKey"
            id="revenueVillage"
            selected={revenueVillage}
            select={setRevenueVillage}
            placeholder={!bpAuthority ? t("SELECT_BP_AUTHORITY_FIRST") : t("SELECT_REVENUE_VILLAGE")}
          />

          {/* Mouza - Either dropdown or text input based on available data */}
          <CardLabel>{`${t("MOUZA")}`} <span className="check-page-link-button">*</span></CardLabel>
          {mouzas.length > 0 ? (
            <Dropdown
              t={t}
              option={mouzas}
              optionKey="i18nKey"
              selected={mouza}
              
              select={setMouza}
              placeholder={!revenueVillage ? t("SELECT_REVENUE_VILLAGE_FIRST") : t("SELECT_MOUZA")} 
            />
          ) : (
            <TextInput
              t={t}
              name="mouza"
              value={mouza}
              onChange={(e) => setMouza(e.target.value)}
              placeholder={`${t("ENTER_MOUZA_NAME")}`}
            />
          )}

          {/* Ward - Always a text input field */}
          <CardLabel>{`${t("WARD")}`} <span className="check-page-link-button">*</span></CardLabel>
          <TextInput
            t={t}
            name="ward"
            value={ward}
            onChange={(e) => setWard(e.target.value)}
            placeholder={`${t("ENTER_WARD_NUMBER")}`}
          />
        </div>
      </FormStep>
    </React.Fragment>
  );
};

export default AreaMapping;